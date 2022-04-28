package me.koba1.tfbbuildffa8.API;

import me.koba1.tfbbuildffa8.Files.DataStorage;
import org.bukkit.entity.Player;

public class FFADataApi {

    public static int getBalance(Player p) {
        try {
            return DataStorage.getData().getInt("Players." + p.getUniqueId() + ".balance");
        } catch (NullPointerException e) {
            return -1;
        }
     }

    private static void set(Player target, int amount) {
        DataStorage.getData().set("Players." + target.getUniqueId() + ".balance", amount);
        DataStorage.saveData();
    }

    public static boolean addBalance(Player p, int amount) {
        try {
            set(p, (int) (amount + getBalance(p)));
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    public static boolean takeBalance(Player p, int amount) {
        try {
            if((getBalance(p) - amount) < 0) {
                return false;
            } else {
                set(p, (int) (getBalance(p) - amount));
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    public static boolean setBalance(Player p, int amount) {
        try {
            set(p, (int) amount);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
