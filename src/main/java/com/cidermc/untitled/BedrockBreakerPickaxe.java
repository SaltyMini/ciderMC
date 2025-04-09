package com.cidermc.untitled;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BedrockBreakerPickaxe {

    public static ItemStack bbPickaxe() {
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text("Bedrock Breaker Pickaxe")
                .color(TextColor.color(0xFFAA00)));
        meta.setEnchantmentGlintOverride(true);

        meta.lore(Arrays.asList(
                Component.text("A one use pickaxe to break"),
                Component.text("bedrock. Use wisely!")
                )
        );



        return item;
    }
}
