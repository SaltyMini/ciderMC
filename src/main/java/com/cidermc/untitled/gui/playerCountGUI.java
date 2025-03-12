package com.cidermc.untitled.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.Arrays;

import static io.papermc.paper.registry.keys.ItemTypeKeys.IRON_INGOT;

public class playerCountGUI implements Listener {
    private final Inventory inv;

    public playerCountGUI() {

    inv = Bukkit.createInventory(null, 9, "Player Count");

        initializeItems();
    }


    public void initializeItems() {
        inv.addItem(createGuiItem(Material.GOLD_INGOT, "Example Gold", "§aFirst line of the lore", "§bSecond line of the lore"));
        inv.addItem(createGuiItem(Material.IRON_INGOT, "Example IRON", "§aFirst line of the lore", "§bSecond line of the lore"));
        inv.addItem(createGuiItem(Material.COPPER_INGOT, "Example COPPER", "§aFirst line of the lore", "§bSecond line of the lore"));

    }


    protected ItemStack createGuiItem(final Material material, final String name, final String lore1, final String lore2) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore1 + lore2));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity humanEntity) {
        humanEntity.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if(!event.getInventory().equals(inv)) return;

        event.setCancelled(true);

        final ItemStack clickedItem = event.getCurrentItem();
        if(clickedItem == null || clickedItem.getType().isAir()) return;

        final Player player = (Player) event.getWhoClicked();

        switch(clickedItem.getType()) {
            case IRON_INGOT:
                player.sendMessage("You clicked Iron");
                break;
            case GOLD_INGOT:
                player.sendMessage("You clicked Gold");
                break;
            case COPPER_INGOT:
                player.sendMessage(("You clicked COpper"));
                break;
        }

    }

    public void onInventoryClick(final InventoryDragEvent event) {
        if(event.getInventory().equals(inv)) {
            event.setCancelled(true);
        }
    }
}
