package me.koba1.tfbbuildffa8.Events.PVP;

import me.koba1.tfbbuildffa8.API.FFAApi;
import me.koba1.tfbbuildffa8.Events.JoinWorldEvent;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.UUID;

public class MoveEvent implements Listener {

    public static FileConfiguration getData() {
        return DataStorage.getData();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getPlayer().getLocation().getY() <= 0) {

                Location loc = new Location(e.getPlayer().getWorld(), 0.5, 121, -1.5, 0, 0);

                e.getPlayer().teleport(loc);
                e.getPlayer().setHealth(20D);
                e.getPlayer().setSaturation(20F);
                //JoinWorldEvent.addItems(e.getPlayer());

                Player p = e.getPlayer();
                try {
                    for (Map.Entry<String, Long> map : Main.pvpTimer.entrySet()) {
                        if (map.getKey().split("//")[0].equalsIgnoreCase(e.getPlayer().getUniqueId().toString())) {
                            if (Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0])) != null) {
                                if (Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1])) != null) {

                                    Player dead = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0]));
                                    Player killer = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1]));

                                    JoinWorldEvent.addItems(dead);

                                    if(Main.pvpTimer.containsKey(dead.getUniqueId() + "//" + killer.getUniqueId())) {
                                        FFAApi.transferDeadItems(dead, killer);
                                    }
                                    FFAApi.removeItems(dead);

                                    int kill = getData().getInt("Players." + killer.getUniqueId() + ".kills");
                                    int balance = getData().getInt("Players." + killer.getUniqueId() + ".balance");
                                    int death = getData().getInt("Players." + dead.getUniqueId() + ".deaths");
                                    int currentStreakKiller = getData().getInt("Players." + killer.getUniqueId() + ".streak");

                                    getData().set("Players." + killer.getUniqueId() + ".kills", kill+1);
                                    getData().set("Players." + killer.getUniqueId() + ".balance", balance+1);
                                    getData().set("Players." + killer.getUniqueId() + ".streak", currentStreakKiller + 1);
                                    getData().set("Players." + dead.getUniqueId() + ".deaths", death+1);
                                    getData().set("Players." + dead.getUniqueId() + ".streak", 0);
                                    DataStorage.saveData();

                                    p.getWorld().getPlayers().forEach(player -> {
                                        player.sendMessage("§e" + p.getName() + " §7meghalt §e" + killer.getName() + " §7keze által.");
                                    });

                                    if((currentStreakKiller + 1) == 3 && !FFAApi.isFully(killer, ItemList.slime())) {
                                        FFAApi.addItem(killer, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 6 && !FFAApi.isFully(killer, ItemList.slime())) {
                                        FFAApi.addItem(killer, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 10 && !FFAApi.isFully(killer, ItemList.slime())) {
                                        FFAApi.addItem(killer, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 15 && !FFAApi.isFully(killer, ItemList.slime())) {
                                        FFAApi.addItem(killer, ItemList.slime());
                                    }

                                    //Main.pvpTimer.remove(killer.getUniqueId() + "//" + dead.getUniqueId());
                                    Main.pvpTimer.remove(map.getKey());
                                    Main.pvpTimerTimer.remove(dead.getUniqueId());
                                    if (Main.pvpTimer.isEmpty()) {
                                        break;
                                    }
                                    return;
                                }
                            }
                        }
                        //
                        else if (map.getKey().split("//")[1].equalsIgnoreCase(e.getPlayer().getUniqueId().toString())) {
                            if (Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0])) != null) {
                                if (Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1])) != null) {

                                    Player dead = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0]));
                                    Player killer = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1]));

                                    JoinWorldEvent.addItems(killer);

                                    if(Main.pvpTimer.containsKey(killer.getUniqueId() + "//" + dead.getUniqueId())) {
                                        FFAApi.transferDeadItems(dead, killer);
                                    }
                                    FFAApi.removeItems(killer);

                                    int kill = getData().getInt("Players." + dead.getUniqueId() + ".kills");
                                    int balance = getData().getInt("Players." + dead.getUniqueId() + ".balance");
                                    int death = getData().getInt("Players." + killer.getUniqueId() + ".deaths");
                                    int currentStreakKiller = getData().getInt("Players." + dead.getUniqueId() + ".streak");

                                    getData().set("Players." + dead.getUniqueId() + ".kills", kill+1);
                                    getData().set("Players." + dead.getUniqueId() + ".balance", balance+1);
                                    getData().set("Players." + dead.getUniqueId() + ".streak", currentStreakKiller+1);
                                    getData().set("Players." + killer.getUniqueId() + ".deaths", death+1);
                                    getData().set("Players." + killer.getUniqueId() + ".streak", 0);
                                    DataStorage.saveData();

                                    if((currentStreakKiller + 1) == 3 && !FFAApi.isFully(dead, ItemList.slime())) {
                                        FFAApi.addItem(dead, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 6 && !FFAApi.isFully(dead, ItemList.slime())) {
                                        FFAApi.addItem(dead, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 10 && !FFAApi.isFully(dead, ItemList.slime())) {
                                        FFAApi.addItem(dead, ItemList.slime());
                                    }
                                    if((currentStreakKiller + 1) == 15 && !FFAApi.isFully(dead, ItemList.slime())) {
                                        FFAApi.addItem(dead, ItemList.slime());
                                    }

                                    Main.pvpTimer.remove(map.getKey());
                                    Main.pvpTimerTimer.remove(killer.getUniqueId());
                                    if (Main.pvpTimer.isEmpty()) {
                                        break;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                } catch (ConcurrentModificationException ex) {

                }
            }
        }
    }
}
