package me.koba1.tfbbuildffa8.Shop;

import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopItems implements Listener {

    public static ItemStack slimeShop() {
        ItemStack is = ItemList.slime();
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(
                "§5",
                "§7Aktiválás esetén egy slime block",
                "§7feljebb dob és meg tudod menteni magad.",
                "§75 másodpercig ráérkezhetnek más játékosok is.",
                "§5",
                "§7Ára: §6✯§6§n" + Main.itemPrices.get(ItemList.slime())
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack platformShop() {
        ItemStack is = ItemList.platform();
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(
                "§5",
                "§7Aktiválás esetén egy 3x3 méretű",
                "§7platform fog alattad teremni 5 másodperci.",
                "§7Erre más játékosok is képesek ráérkezni",
                "§5",
                "§7Ára: §6✯§6§n" + Main.itemPrices.get(ItemList.platform())
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack goldenShop() {
        ItemStack is = ItemList.goldenapple();
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(
                "§5",
                "§7Ára: §6✯§6§n" + Main.itemPrices.get(ItemList.goldenapple())
        ));
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack enderShop() {
        ItemStack is = ItemList.enderpearl();
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(
                "§5",
                "§7Ára: §6✯§6§n" + Main.itemPrices.get(ItemList.enderpearl())
        ));
        is.setItemMeta(im);
        return is;
    }
}
