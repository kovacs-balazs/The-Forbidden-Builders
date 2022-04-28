package me.koba1.tfbbuildffa8.Events.PVP;

import me.koba1.tfbbuildffa8.API.ActionBar;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.UUID;

public class PvPTimerEvent implements Listener {

    private static FileConfiguration getData() {
        return DataStorage.getData();
    }

    private static Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {
        if(e.getEntity().getWorld().getName().equalsIgnoreCase("buildffa")
                && e.getDamager().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if (e.getEntity() instanceof Player) {
                if (e.getDamager() instanceof Player) {

                    if(e.isCancelled()) return;

                    Player p = (Player) e.getEntity();
                    Player damager = (Player) e.getDamager();

                    long unixTime = System.currentTimeMillis() / 1000;
                    Main.pvpTimer.put(p.getUniqueId() + "//" + damager.getUniqueId(), unixTime + 10);
                    Main.pvpTimerTimer.put(p.getUniqueId(), unixTime + 10);
                    Main.pvpTimerTimer.put(damager.getUniqueId(), unixTime + 10);
                }
            }
        }
    }

    public static void pvpTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long currentUnix = System.currentTimeMillis() / 1000;
                if(!Main.pvpTimerTimer.isEmpty()) {
                    for (Map.Entry<UUID, Long> hash : Main.pvpTimerTimer.entrySet()) {
                        if (Bukkit.getPlayer(hash.getKey()) != null) {
                            if (hash.getValue() >= currentUnix) {
                                int sec = (int) (hash.getValue() - currentUnix);
                                Player p = Bukkit.getPlayer(hash.getKey());
                                ActionBar.sendActionbar(p, ActionBar.getActionBar(sec));
                            }
                        }
                    }
                }
                if(!Main.pvpTimer.isEmpty()) {
                    try {
                        for (Map.Entry<String, Long> map : Main.pvpTimer.entrySet()) {
                            if (map.getValue() >= currentUnix) {
                                //int sec = (int) (map.getValue() - currentUnix);
                            /*if(Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0])) != null) {
                                Player p = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[0]));
                                //p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ActionBar.getActionBar(sec)));
                            }

                            if(Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1])) != null) {
                                Player k = Bukkit.getPlayer(UUID.fromString(map.getKey().split("//")[1]));
                                //k.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ActionBar.getActionBar(sec)));
                            }*/
                            } else {
                                Main.pvpTimer.remove(map.getKey());
                                if (Main.pvpTimer.size() == 0) {
                                    break;
                                }
                            }
                        }
                    } catch (ConcurrentModificationException ex) {

                    }
                }
            }
        }.runTaskTimer(m, 0L, 10L);
    }
}
