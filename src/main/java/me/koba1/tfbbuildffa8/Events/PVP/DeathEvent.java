package me.koba1.tfbbuildffa8.Events.PVP;

import me.koba1.tfbbuildffa8.API.FFAApi;
import me.koba1.tfbbuildffa8.Events.JoinWorldEvent;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {

    private Main m = Main.getPlugin(Main.class);

    public static FileConfiguration getData() {
        return DataStorage.getData();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent e) {
        if(e.getEntity().getWorld().getName().toLowerCase().equals("buildffa")) {
            if(e.getEntity() instanceof Player) {
                if (e.getEntity().getKiller() instanceof Player) {
                    Player p = e.getEntity();
                    if (e.getEntity().getKiller() != null) {
                        Player killer = e.getEntity().getKiller();
                        int kill = getData().getInt("Players." + killer.getUniqueId() + ".kills");
                        int balance = getData().getInt("Players." + killer.getUniqueId() + ".balance");
                        int death = getData().getInt("Players." + p.getUniqueId() + ".deaths");
                        int currentStreakKiller = getData().getInt("Players." + killer.getUniqueId() + ".streak");

                        getData().set("Players." + killer.getUniqueId() + ".kills", kill+1);
                        getData().set("Players." + killer.getUniqueId() + ".balance", balance+1);
                        getData().set("Players." + killer.getUniqueId() + ".streak", currentStreakKiller + 1);
                        getData().set("Players." + p.getUniqueId() + ".deaths", death+1);
                        getData().set("Players." + p.getUniqueId() + ".streak", 0);
                        DataStorage.saveData();

                        FFAApi.transferDeadItems(e.getEntity(), killer);

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

                        p.getWorld().getPlayers().forEach(player -> {
                            player.sendMessage("§e" + p.getName() + " §7meghalt §e" + killer.getName() + " §7keze által.");
                        });

                        p.getInventory().clear();
                        e.setDroppedExp(0);
                        e.setKeepInventory(true);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                p.spigot().respawn();
                            }
                        }.runTaskLater(m, 1L);

                        if(Main.pvpTimerTimer.containsKey(e.getEntity().getUniqueId())) {
                            Main.pvpTimerTimer.remove(e.getEntity().getUniqueId());
                            //
                            // e.getEntity().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            Location loc = new Location(e.getPlayer().getWorld(), 0.5, 121, -1.5, 0, 0);
            e.setRespawnLocation(loc);
            JoinWorldEvent.addItems(e.getPlayer());
            e.getPlayer().setHealth(20D);
            e.getPlayer().setSaturation(20F);
        }
    }
}
