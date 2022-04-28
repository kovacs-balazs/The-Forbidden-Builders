package me.koba1.tfbbuildffa8;

import me.koba1.tfbbuildffa8.API.FFADataApi;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import org.bukkit.entity.Player;

public class FFAPlayerAPI {

    public static int getBalance(Player target) {
        return FFADataApi.getBalance(target);
    }

    public static int getKills(Player target) {
        try {
            return DataStorage.getData().getInt("Players." + target.getUniqueId() + ".kills");
        }catch (NullPointerException e) {
            return -1;
        }
    }

    public static int getDeaths(Player target) {
        try {
            return DataStorage.getData().getInt("Players." + target.getUniqueId() + ".deaths");
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static int getStreak(Player target) {
        try {
            return DataStorage.getData().getInt("Players." + target.getUniqueId() + ".streak");
        } catch (NullPointerException e) {
            return -1;
        }
    }
}
