package com.cidermc.untitled.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class ranksGUI implements Listener {
    // Border item

    /**
     * Creates a permission-based item that shows different appearance based on permissions
     */
    public Item createPermissionItem(Material unlockedMaterial, Material lockedMaterial,
                                     String displayName, String permission, String lore1, String lore2, String lore3, String lore4, String lore5, String lore6, String lore7) {
        return new AbstractItem() {

            @Override
            public ItemProvider getItemProvider(Player player) {
                // Check if player has permission
                boolean hasPermission = player.hasPermission(permission);

                // Create different ItemBuilder based on permission
                ItemBuilder builder;
                if (hasPermission) {
                    // Unlocked item appearance
                    builder = new ItemBuilder(unlockedMaterial)
                            .setDisplayName("§a" + displayName + " §7(Unlocked)")
                            .addLoreLines(lore1, lore2, lore3, lore4, lore5, lore6, lore7);
                } else {
                    // Locked item appearance
                    builder = new ItemBuilder(lockedMaterial)
                            .setDisplayName("§c" + displayName + " §7(Locked)")
                            .addLoreLines(lore1, lore2, lore3, lore4, lore5, lore6, lore7);
                }

                return builder;
            }

            @Override
            public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
                Item item = (Item) inventoryClickEvent.getCurrentItem();


            }


            @EventHandler
            public void onInventoryClick(final InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                boolean hasPermission = player.hasPermission(permission);

                if (hasPermission) {
                    // Do something when player clicks and has permission
                    player.sendMessage("§aYou selected " + displayName + "!");
                } else {
                    // Do something when player clicks but doesn't have permission
                    player.sendMessage("§cYou don't have permission to use " + displayName + "!");
                }
            }
        };
    }

    // Creating permission-based rank items
    Item rank1 = createPermissionItem(Material.DIAMOND, Material.BARRIER, "Guest", "cider.ranks.guest", "- 1 Home", "- 1 Job", "- /kit Starter", "- /sell", "", "", "");
    Item rank2 = createPermissionItem(Material.GOLD_INGOT, Material.BARRIER, "Tanner", "cider.ranks.tanner", "- Has all permissions from previous rank", "- +7,500 Claim Blocks","- /pw", "- /poop", "", "", "");
    Item rank3 = createPermissionItem(Material.EMERALD, Material.BARRIER, "Criminal", "cider.ranks.criminal", "- Has all permissions from previous rank", "- Ability to purchase 2 Lottery Tickets", "- Access to 1 Dungeon Attempt", "- /fart", "- /sit", "", "");
    Item rank4 = createPermissionItem(Material.NETHER_STAR, Material.BARRIER, "Peasant", "cider.ranks.peasant", "- Has all permissions from previous rank", "- Ability to create shops", "- 3 Homes","- /tptoggle", "- /bottle", "- /crawl", "");
    Item rank5 = createPermissionItem(Material.DRAGON_EGG, Material.BARRIER, "Farmer", "cider.ranks.farmer", "- Has all permissions from previous rank", "- +2,500 Claim Blocks", "- Ability to purchase 3 Lottery Tickets", "- Ability to purchase plots at the market", "- 2 Jobs", "- /tpahere", "");

    Item rank6 = createPermissionItem(Material.ENDER_EYE, Material.BARRIER, "Artist", "cider.ranks.artist", "- Has all permissions from previous rank", "- Ability to purchase 5 Lottery Tickets", "- 1 Seasonal Key", "- 4 Homes", "- /condense", "- /lay", "- /sell hand");
    Item rank7 = createPermissionItem(Material.BEACON, Material.BARRIER, "Sculptor", "cider.ranks.sculptor", "- Has all permissions from previous rank", "- Ability to purchase 6 Lottery Tickets", "- Ability to sit on top of players", "- Access to 2 Dungeon Attempts", "- /craft", "- /feed (60 second cooldown)", "");
    Item rank8 = createPermissionItem(Material.ELYTRA, Material.BARRIER, "Priest", "cider.ranks.priest", "- Has all permissions from previous rank", "- +5,000 Claim Blocks", "Ability to set 1 Player Warp", "- 5 Homes", "- 3 Jobs", "- /anvil", "");
    Item rank9 = createPermissionItem(Material.TRIDENT, Material.BARRIER, "High Priest", "cider.ranks.highpriest", "- Has all permissions from previous rank", "- Ability to purchase 10 Lottery Tickets", "- Ability to list 1 item in /ah", "- 6 Homes", "- /marry", "", "");
    Item rank10 = createPermissionItem(Material.TOTEM_OF_UNDYING, Material.BARRIER, "Ronin", "cider.ranks.ronin", "- Has all permissions from previous rank", "- Ability to set 2 Player Warps", "- Access to all balloon cosmetics", "- 7 Homes", "- /sell inventory", "", "");

    Item rank11 = createPermissionItem(Material.ENDER_EYE, Material.BARRIER, "Samurai", "cider.ranks.samurai", "- Has all permissions from previous rank", "- Access to all music cosmetics", "- 4 Jobs", "- /spin", "- /loom", "", "");
    Item rank12 = createPermissionItem(Material.BEACON, Material.BARRIER, "Minister", "cider.ranks.minister", "- Has all permissions from previous rank", "- Access to all mount cosmetics", "- Access to 3 Dungeon Attempts", "Ability to set 3 Player Warps", "- 2 Rare Keys","- /loom",  "");
    Item rank13 = createPermissionItem(Material.ELYTRA, Material.BARRIER, "Shinpan", "cider.ranks.shinpan", "- Has all permissions from previous rank", "- Access to X", "- Access to all emote cosmetics", "- 8 Homes", "- /clearinventory", "", "");
    Item rank14 = createPermissionItem(Material.TRIDENT, Material.BARRIER, "Daimyo", "cider.ranks.daimyo", "- Has all permissions from previous rank", "- Access to all banner cosmetics", "- 5 Jobs", "- /trash", "", "", "");
    Item rank15 = createPermissionItem(Material.TOTEM_OF_UNDYING, Material.BARRIER, "Chancellor", "cider.ranks.chancellor", "- Has all permissions from previous rank", "- Ability to set 4 Player Warps", "- 9 Homes", "- /back", "", "", "");

    Item rank16 = createPermissionItem(Material.ENDER_EYE, Material.BARRIER, "Shogun", "cider.ranks.shogun","- Has all permissions from previous rank", "- 10 Homes", "- /feed (no cooldown)", "- /rtp (no cooldown)", "- /fix", "", "");
    Item rank17 = createPermissionItem(Material.BEACON, Material.BARRIER, "Emperor", "cider.ranks.emperor", "- Has all permissions from previous rank", "- Ability to *prestige*", "- 2 Seasonal Keys", "-3 Rare Keys", "- +10,000 Claim Blocks", "", "");

    final Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));
    // Build the GUI
    Gui gui = Gui.normal()
            .setStructure(
                    "# # # # # # # # #",
                    "# 1 2 3 4 5 6 7 #",
                    "# # 8 9 0 < > # #",
                    "# # = - . ] [ # #",
                    "# # # # # # # # #")
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
            .addIngredient(']', rank16)
            .addIngredient('[', rank17)
            // Add other items like back, next buttons as needed
            .build();

    public void onInventoryClick(final InventoryDragEvent event) {
        if(event.getInventory().equals(gui)) {
            event.setCancelled(true);
            //handled block click
        }
    }


    public void openGui(Player player) {
        String title = "Ranks";
        Window window = Window.single()
                .setViewer(player)
                .setTitle(title)
                .setGui(gui)
                .build();

        //To open the GUI use ranksGUI.getInstance().openGui(player);
    }
}
