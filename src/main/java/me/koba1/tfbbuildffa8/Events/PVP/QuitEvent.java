package me.koba1.tfbbuildffa8.Events.PVP;

import me.koba1.tfbbuildffa8.API.FFAApi;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class QuitEvent implements Listener {

    public static FileConfiguration getData() {
        return DataStorage.getData();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(Main.pvpTimerTimer.containsKey(e.getPlayer().getUniqueId()))
                Main.pvpTimerTimer.remove(e.getPlayer().getUniqueId());
            for(Map.Entry<String, Long> map : Main.pvpTimer.entrySet()) {
                if(map.getKey().contains(e.getPlayer().getUniqueId().toString())) {

                    Main.pvpTimer.remove(map.getKey());

                    if(Bukkit.getOfflinePlayer(UUID.fromString(map.getKey().split("//")[0])) != null) {
                        Player p = (Player) Bukkit.getOfflinePlayer(UUID.fromString(map.getKey().split("//")[0]));
                        Player killer = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1]));

                        int kill = getData().getInt("Players." + killer.getUniqueId() + ".kills");
                        int balance = getData().getInt("Players." + killer.getUniqueId() + ".balance");
                        int death = getData().getInt("Players." + p.getUniqueId() + ".deaths");
                        int currentStreakKiller = getData().getInt("Players." + killer.getUniqueId() + ".streak");

                        FFAApi.transferDeadItems(p, killer);
                        FFAApi.removeItems(p);

                        getData().set("Players." + killer.getUniqueId() + ".kills", kill+1);
                        getData().set("Players." + killer.getUniqueId() + ".balance", balance+1);
                        getData().set("Players." + killer.getUniqueId() + ".streak", currentStreakKiller+1);
                        getData().set("Players." + p.getUniqueId() + ".deaths", death+1);
                        getData().set("Players." + p.getUniqueId() + ".streak", 0);
                        DataStorage.saveData();

                        if((currentStreakKiller + 1) == 3 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 6 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 10 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 15 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }

                        p.getWorld().getPlayers().forEach(player -> {
                            player.sendMessage("§e" + e.getPlayer().getName() + " §7kilépett harc közben.");
                        });

                    } else if(Bukkit.getOfflinePlayer(UUID.fromString(map.getKey().split("//")[1])) != null) { // killer lép ki
                        Player p = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0]));
                        Player killer = (Player) Bukkit.getOfflinePlayer(UUID.fromString(map.getKey().split("//")[1]));

                        int kill = getData().getInt("Players." + p.getUniqueId() + ".kills");
                        int balance = getData().getInt("Players." + p.getUniqueId() + ".balance");
                        int death = getData().getInt("Players." + killer.getUniqueId() + ".deaths");
                        int currentStreakKiller = getData().getInt("Players." + p.getUniqueId() + ".streak");

                        FFAApi.transferDeadItems(killer, p);
                        FFAApi.removeItems(killer);

                        getData().set("Players." + p.getUniqueId() + ".kills", kill+1);
                        getData().set("Players." + p.getUniqueId() + ".balance", balance+1);
                        getData().set("Players." + p.getUniqueId() + ".streak", currentStreakKiller+1);
                        getData().set("Players." + killer.getUniqueId() + ".deaths", death+1);
                        getData().set("Players." + killer.getUniqueId() + ".streak", 0);
                        DataStorage.saveData();

                        if((currentStreakKiller + 1) == 3 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 6 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 10 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }
                        if((currentStreakKiller + 1) == 15 && !FFAApi.isFully(killer, ItemList.slime())) {
                            FFAApi.addItem(p, ItemList.slime());
                        }

                        p.getWorld().getPlayers().forEach(player -> {
                            player.sendMessage("§e" + e.getPlayer().getName() + " §7kilépett harc közben.");
                        });
                    }
                }
                if(Main.pvpTimer.isEmpty()) {
                    return;
                }
            }
        }
    }
}
