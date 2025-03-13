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
                                     String displayName, String permission, String lore1, String lore2, String lore3, String lore4, String lore5, String lore6) {
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
                            .addLoreLines(lore1, lore2, lore3, lore4, lore5, lore6);
                } else {
                    // Locked item appearance
                    builder = new ItemBuilder(lockedMaterial)
                            .setDisplayName("§c" + displayName + " §7(Locked)")
                            .addLoreLines(lore1, lore2, lore3, lore4, lore5, lore6);
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
    Item rank1 = createPermissionItem(Material.DIAMOND, Material.BARRIER, "Gues", "cider.ranks.guest", "", "", "", "", "", "");
    Item rank2 = createPermissionItem(Material.GOLD_INGOT, Material.BARRIER, "MVP", "cider.ranks.mvp", "", "", "", "", "", "");
    Item rank3 = createPermissionItem(Material.EMERALD, Material.BARRIER, "ELITE", "ranks.elite", "", "", "", "", "", "");
    Item rank4 = createPermissionItem(Material.NETHER_STAR, Material.BARRIER, "LEGEND", "ranks.legend", "", "","", "", "", "");
    Item rank5 = createPermissionItem(Material.DRAGON_EGG, Material.BARRIER, "GOD", "ranks.god", "", "", "", "", "", "");

    Item rank6 = createPermissionItem(Material.ENDER_EYE, Material.BARRIER, "SPECIAL1", "ranks.special1", "", "", "", "", "", "");
    Item rank7 = createPermissionItem(Material.BEACON, Material.BARRIER, "SPECIAL2", "ranks.special2", "", "", "", "", "", "");
    Item rank8 = createPermissionItem(Material.ELYTRA, Material.BARRIER, "SPECIAL3", "ranks.special3", "", "", "", "", "", "");
    Item rank9 = createPermissionItem(Material.TRIDENT, Material.BARRIER, "SPECIAL4", "ranks.special4", "", "", "", "", "", "");
    Item rank10 = createPermissionItem(Material.TOTEM_OF_UNDYING, Material.BARRIER, "SPECIAL5", "ranks.special5", "", "", "", "", "", "");

    Item rank11 = createPermissionItem(Material.ENDER_EYE, Material.BARRIER, "SPECIAL1", "ranks.special1", "", "", "", "", "", "");
    Item rank12 = createPermissionItem(Material.BEACON, Material.BARRIER, "SPECIAL2", "ranks.special2", "", "", "", "", "", "");
    Item rank13 = createPermissionItem(Material.ELYTRA, Material.BARRIER, "SPECIAL3", "ranks.special3", "", "", "", "", "", "");
    Item rank14 = createPermissionItem(Material.TRIDENT, Material.BARRIER, "SPECIAL4", "ranks.special4", "", "", "", "", "", "");
    Item rank15 = createPermissionItem(Material.TOTEM_OF_UNDYING, Material.BARRIER, "SPECIAL5", "ranks.special5", "", "", "", "", "", "");

    final Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));
    // Build the GUI
    Gui gui = Gui.normal()
            .setStructure(
                    "# # # # # # # # #",
                    "# # 1 2 3 4 5 # #",
                    "# # 6 7 8 9 0 # #",
                    "# # < > = - . # #",
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
