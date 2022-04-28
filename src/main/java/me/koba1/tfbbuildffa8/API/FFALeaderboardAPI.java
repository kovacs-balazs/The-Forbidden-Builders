package me.koba1.tfbbuildffa8.API;

import me.koba1.tfbbuildffa8.Files.DataStorage;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class FFALeaderboardAPI {

    private static HashMap<UUID, Integer> hashMap = new HashMap<>();

    private static FileConfiguration getData() {
        DataStorage.relaodData();
        return DataStorage.getData();
    }


    private static void fillHashMap() {
        hashMap.clear();
        DataStorage.relaodData();
        for(String str : getData().getConfigurationSection("Players").getKeys(false)) {
            hashMap.put(UUID.fromString(str), getData().getInt("Players." + str + ".kills"));
        }
    }

    public static HashMap<UUID, Integer> getLeaderboard() {

        fillHashMap();
        LinkedHashMap<UUID, Integer> reverseSortedMap = new LinkedHashMap<>();
        HashMap<UUID, Integer> returnHash = new HashMap<>();

        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        int i = 1;
        try {
            if(reverseSortedMap.isEmpty())
                return returnHash;
            for (Map.Entry<UUID, Integer> hash : reverseSortedMap.entrySet()) {
                if (!(hash.getValue() > 0)) {
                    reverseSortedMap.remove(hash.getKey());
                } else {
                    returnHash.put(hash.getKey(), i);
                    i++;
                }
            }
            return returnHash;
        } catch (ConcurrentModificationException e) {
            return returnHash;
        }
    }
}
