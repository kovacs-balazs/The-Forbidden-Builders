package me.koba1.tfbbuildffa8.API;

import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Main;
import me.koba1.tfbbuildffa8.Shop.ShopGUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FFABuyAPI {

    public static FileConfiguration getData() {
        return DataStorage.getData();
    }

    public static void buyItem(Player p, ItemStack is) {
        int balance = getData().getInt("Players." + p.getUniqueId() + ".balance");
        if(balance >= Main.itemPrices.get(is)) {
            if(!FFAApi.isFully(p, is)) {
                FFAApi.addItem(p, is);

                p.sendMessage("§7[§cBolt§7] §aSikeresen vásárlás!");
                p.openInventory(ShopGUI.inv(p));
                getData().set("Players." + p.getUniqueId() + ".balance", (balance - Main.itemPrices.get(is)));
                DataStorage.saveData();
            } else {
                p.sendMessage("§7[§cBolt§7] §cSikeretelen vásárlás§7. Az eszköztáradban már ebből a tárgyból nem fér el több.");
            }
        } else {
            p.sendMessage("§7[§cBolt§7] §cSikeretelen vásárlás§7. Ehhez nincs elég pénzed.");
        }
    }
}
