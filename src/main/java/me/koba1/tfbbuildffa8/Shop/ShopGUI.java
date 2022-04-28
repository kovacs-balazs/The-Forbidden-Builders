package me.koba1.tfbbuildffa8.Shop;

import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Number.NumberEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ShopGUI {

    public static Inventory inv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 4 * 9, "§8Bolt");

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, NumberEvent.glass());
        }

        inv.setItem(10, ShopItems.enderShop());
        inv.setItem(12, ShopItems.goldenShop());
        inv.setItem(14, ShopItems.platformShop());
        inv.setItem(16, ShopItems.slimeShop());

        inv.setItem(31, NumberEvent.quit());
        inv.setItem(35, balance(p));

        return inv;
    }

    public static ItemStack balance(Player p) {
        ItemStack is = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§aEgyenleged: §2✯§2§n" + DataStorage.getData().getInt("Players." + p.getUniqueId() + ".balance"));
        im.setLore(Arrays.asList(
                "§5",
                "§7\"Pénzt\" ölések után kapsz, mindig egyet.",
                "§7A szerveren az egyenleg virtuális",
                "§7pénzként működik.",
                "§7(Vásárláskor statisztikád nem változik!)."
        ));
        im.addEnchant(Enchantment.DURABILITY, 2, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(im);
        return is;
    }
}
