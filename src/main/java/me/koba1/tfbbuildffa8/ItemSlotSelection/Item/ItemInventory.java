package me.koba1.tfbbuildffa8.ItemSlotSelection.Item;

import me.koba1.tfbbuildffa8.ItemSlotSelection.Number.NumberEvent;
import me.koba1.tfbbuildffa8.Items.ItemList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemInventory {

    public static Inventory inv() {
        Inventory inv = Bukkit.createInventory(null, 2*9, "§8Tárgyválasztó");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, NumberEvent.glass());
        }

        inv.setItem(1, ItemList.stick());
        inv.setItem(3, ItemList.wool());
        inv.setItem(7, ItemList.goldenapple());
        inv.setItem(5, ItemList.platform());
        inv.setItem(4, ItemList.slime());
        inv.setItem(2, ItemList.kard());
        inv.setItem(6, ItemList.enderpearl());
        inv.setItem(13, NumberEvent.quit());
        inv.setItem(17, reset());
        return inv;
    }

    public static ItemStack reset() {
        ItemStack is = new ItemStack(Material.BARRIER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§cVisszaállítás");
        im.setLore(Arrays.asList(
                "§5",
                "§7Kattints ide a tárgy helyek",
                "§7visszaállításához!"
        ));
        is.setItemMeta(im);
        return is;
    }
}
