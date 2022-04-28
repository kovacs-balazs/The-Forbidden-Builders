package me.koba1.tfbbuildffa8.Items;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemList {

    public static ItemStack stick() {
        ItemStack is = new ItemStack(Material.STICK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§a§oKnockback Stick");
        im.addEnchant(Enchantment.KNOCKBACK, 2, false);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack goldenapple() {
        ItemStack is = new ItemStack(Material.GOLDEN_APPLE);
        return is;
    }

    public static ItemStack enderpearl() {
        ItemStack is = new ItemStack(Material.ENDER_PEARL);
        return is;
    }

    public static ItemStack slime() {
        ItemStack is = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§b§lSlimy Jump");
        im.setLore(Arrays.asList(
                "§5",
                "§7Aktiválás esetén egy slime block",
                "§7feljebb dob és meg tudod menteni magad.",
                "§75 másodpercig ráérkezhetnek más játékosok is.",
                "§5",
                "§cJobb klikk §7az aktiváláshoz!"
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack kard() {
        ItemStack is = new ItemStack(Material.WOOD_SWORD);
        ItemMeta im = is.getItemMeta();
        //im.setUnbreakable(true);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack platform() {
        ItemStack is = new ItemStack(Material.STONE_PLATE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§6§lPlatform");
        im.setLore(Arrays.asList(
                "§5",
                "§7Aktiválás esetén egy 3x3 méretű",
                "§7platform fog alattad teremni 5 másodpercig.",
                "§7Erre más játékosok is képesek ráérkezni.",
                "§5",
                "§cJobb klikk §7az aktiváláshoz!"
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack wool() {
        ItemStack is = new ItemStack(Material.WOOL, 64, DyeColor.WHITE.getWoolData());
        is.setAmount(64);
        return is;
    }
}
