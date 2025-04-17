package com.cidermc.untitled.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class RanksGUI implements Listener {

    /**
     * Creates a permission-based item that shows different appearance based on permissions
     */
    public Item createPermissionItem(Material unlockedMaterial, Material lockedMaterial,
                                     String displayName, String permission, String[] requirements,
                                     String[] bonuses, String... loreLines) {
        return new AbstractItem() {

            @Override
            public ItemProvider getItemProvider(Player player) {
                boolean hasPermission = player.hasPermission(permission);

                ItemBuilder builder = new ItemBuilder(hasPermission ? unlockedMaterial : lockedMaterial)
                        .setDisplayName("&6" + displayName);

                for (String line : loreLines) {
                    builder.addLoreLines("&7" + line);
                }

                builder.addLoreLines("&7&b-------------"); //spacer

                for (String requirement : requirements) {

                    //if they have the rank make it green
                    if(player.hasPermission(permission)) {
                        builder.addLoreLines("&7" + requirement);
                    } else {

                        if(RankPlayerHandle.hasRequirementMoney(player, requirements[0])) {
                            builder.addLoreLines("&a" + requirement);
                        } else {
                            builder.addLoreLines("&c" + requirement);
                        }

                        if(requirements.length > 1 && requirements[1] != null) { //if there is nothing there dont add the lore
                            if(RankPlayerHandle.hasRequirementMCMMO(player, requirements[1])) {
                                builder.addLoreLines("&a" + requirement);
                            } else {
                                builder.addLoreLines("&c" + requirement);
                            }
                        }

                    }
                }
                return builder;

            }

            @Override
            public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                Material clickedMaterial = Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType();
                player.sendMessage("Clicked item: " + clickedMaterial); // Debug message

                boolean hasPermission = player.hasPermission(permission);
                if(hasPermission) {
                    player.sendMessage("You already have the rank " + displayName + "!");
                    return;
                }

                int slot = inventoryClickEvent.getRawSlot();

                if(clickedMaterial.equals(Material.GOLD_BLOCK)) {
                    player.sendMessage("Confirmed");
                    try {
                        RankPlayerHandle.playerRankUpAttempt(player, displayName, requirements, bonuses);
                    } catch (Exception e) {
                        player.sendMessage("Error attempting to rank up " + displayName + "!");
                        player.sendMessage(e.getMessage());
                    }
                    return;
                }

                // Check previous rank only if not clicking gold block
                Material previousRankMaterial = Objects.requireNonNull(inventoryClickEvent.getInventory().getItem(slot - 1)).getType();
                if(previousRankMaterial.equals(Material.RED_WOOL) || previousRankMaterial.equals(Material.GOLD_BLOCK)) {
                    player.sendMessage("You cannot rank up to " + displayName + " without having the rank below it!");
                    return;
                }

                // Create confirmation button with click handling
                Item confirmItem = new AbstractItem() {
                    @Override
                    public ItemProvider getItemProvider(Player p) {
                        return new ItemBuilder(Material.GOLD_BLOCK)
                                .setDisplayName("Click again to confirm");
                    }

                    @Override
                    public void handleClick(@NotNull ClickType clickType, @NotNull Player p, @NotNull InventoryClickEvent event) {
                        Material material = Objects.requireNonNull(event.getCurrentItem()).getType();
                        player.sendMessage("Confirmation clicked: " + material);
                        if(material.equals(Material.GOLD_BLOCK)) {
                            try {
                                RankPlayerHandle.playerRankUpAttempt(p, displayName, requirements, bonuses);
                            } catch (Exception e) {
                                p.sendMessage("Error attempting to rank up " + displayName + "!");
                                p.sendMessage(e.getMessage());
                            }
                        }
                    }
                };

                gui.setItem(slot, confirmItem);
            }

        };
    }

    //Basic format, Unlocked Material, Locked Materical, Display name, Permission, requirements(string array), bonuses(string array), lore...
    //                                                    cider.ranks.(rank name), MUST BE IN THIS FORMAT    , MUST BE IN THIS FORMAT, lore...
    //                                                                             $moneyAmount              , claimBlocksAmount claimBlocks
    //                                                                             mcMMOPowerLevel whatever  , rareKeyAmount rareKey
    //                                                                                                       , seasonalKeyAmount seasonalKey

    Item rank1 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL, "Guest",
            "cider.ranks.guest",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- 1 Home", "- 1 Job", "- /kit Starter", "- /sell");
    Item rank2 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Tanner",
            "cider.ranks.tanner",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- +7,500 Claim Blocks","- /pw", "- /poop");
    Item rank3 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Criminal",
            "cider.ranks.criminal",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to purchase 2 Lottery Tickets", "- Access to 1 Dungeon Attempt", "- /fart", "- /sit");
    Item rank4 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Peasant",
            "cider.ranks.peasant",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to create shops", "- 3 Homes","- /tptoggle", "- /bottle", "- /crawl");
    Item rank5 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Farmer", "cider.ranks.farmer",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- +2,500 Claim Blocks", "- Ability to purchase 3 Lottery Tickets", "- Ability to purchase plots at the market", "- 2 Jobs", "- /tpahere");

    Item rank6 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Artist",
            "cider.ranks.artist",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to purchase 5 Lottery Tickets", "- 1 Seasonal Key", "- 4 Homes", "- /condense", "- /lay", "- /sell hand");
    Item rank7 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Sculptor",
            "cider.ranks.sculptor",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to purchase 6 Lottery Tickets", "- Ability to sit on top of players", "- Access to 2 Dungeon Attempts", "- /craft", "- /feed (60 second cooldown)");
    Item rank8 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Priest",
            "cider.ranks.priest",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- +5,000 Claim Blocks", "Ability to set 1 Player Warp", "- 5 Homes", "- 3 Jobs", "- /anvil");
    Item rank9 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "High Priest",
            "cider.ranks.highpriest",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to purchase 10 Lottery Tickets", "- Ability to list 1 item in /ah", "- 6 Homes", "- /marry");
    Item rank10 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Ronin",
            "cider.ranks.ronin",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to set 2 Player Warps", "- Access to all balloon cosmetics");

    Item rank11 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Samurai",
            "cider.ranks.samurai",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Access to all music cosmetics", "- 4 Jobs", "- /spin", "- /loom", "", "");
    Item rank12 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Minister",
            "cider.ranks.minister",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
  "- Access to all mount cosmetics", "- Access to 3 Dungeon Attempts", "Ability to set 3 Player Warps", "- 2 Rare Keys","- /loom",  "");
    Item rank13 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Shinpan",
            "cider.ranks.shinpan",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Access to X", "- Access to all emote cosmetics", "- 8 Homes", "- /clearinventory", "", "");
    Item rank14 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Daimyo",
            "cider.ranks.daimyo",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Access to all banner cosmetics", "- 5 Jobs", "- /trash", "", "", "");
    Item rank15 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Chancellor",
            "cider.ranks.chancellor",
            new String[]{"$100"},
            new String[]{"2500 claim blocks", "1 rareKey", "0 seasonalKey"},
            "- Ability to set 4 Player Warps", "- 9 Homes", "- /back", "", "", "");

    Item rank16 = createPermissionItem(Material.GREEN_WOOL, Material.RED_WOOL,
            "Shogun",
            "cider.ranks.shogun",
            new String[]{"$100"},
            new String[]{"2500 claim blocks"},
            "- Has all permissions from previous rank",
            "- 10 Homes", "- /feed (no cooldown)", "- /rtp (no cooldown)", "- /fix", "", "");

    private Gui gui;
    private final Map<Player, Window> playerWindows = new HashMap<>();

    public void openGui(Player player) {
        player.sendMessage("Opening ranks GUI...");
        String title = "Ranks";

        final Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));
        // Build the GUI
        this.gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # 1 2 3 4 5 # #",
                        "# # 6 7 8 9 0 # #",
                        "# # < > = - . # #",
                        "# # # # _ # # # #",
                        "# # # # # # # # #"
                )
                .addIngredient('#', border)
                .addIngredient('1', rank1)
                .addIngredient('2', rank2)
                .addIngredient('3', rank3)
                .addIngredient('4', rank4)
                .addIngredient('5', rank5)
                .addIngredient('6', rank6)
                .addIngredient('7', rank7)
                .addIngredient('8', rank8)
                .addIngredient('9', rank9)
                .addIngredient('0', rank10)
                .addIngredient('<', rank11)
                .addIngredient('>', rank12)
                .addIngredient('=', rank13)
                .addIngredient('-', rank14)
                .addIngredient('.', rank15)
                .addIngredient('_', rank16)

                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle(title)
                .setGui(gui)
                .build();

        playerWindows.put(player, window);
        window.open();
    }

    public void closeGui(Player player) {
        Window window = playerWindows.get(player);
        if (window != null) {
            window.close();
            playerWindows.remove(player);
        }
    }
}
