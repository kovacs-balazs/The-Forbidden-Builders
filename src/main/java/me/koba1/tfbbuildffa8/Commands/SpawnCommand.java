package me.koba1.tfbbuildffa8.Commands;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class SpawnCommand implements Listener {

    private Main m = Main.getPlugin(Main.class);
    private static HashMap<UUID, Integer> hashMap = new HashMap<>();

    public void SpawnCommand(Player p) {
        if(p.getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(!Main.pvpTimerTimer.containsKey(p.getUniqueId())) {
                p.sendMessage("§7[§6Teleport§7] §7A teleportálás visszaszámláló elkezdődött, §c3 másodperc §7múlva teleportálunk!");
                hashMap.put(p.getUniqueId(), 0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(hashMap.isEmpty()) {
                            p.sendMessage("§7[§6Teleport§7] §cA teleportálás megszakadt!");
                            cancel();
                            return;
                        }
                        int i = hashMap.get(p.getUniqueId());
                        if(hashMap.containsKey(p.getUniqueId())) {
                            if (!Main.pvpTimerTimer.containsKey(p.getUniqueId())) {
                                if(i >= 60) {
                                    Location loc = new Location(p.getWorld(), 0.5, 121, -1.5, 0, 0);
                                    p.teleport(loc);
                                    hashMap.remove(p.getUniqueId());
                                    cancel();
                                    return;
                                }
                            } else {
                                p.sendMessage("§7[§cHarc§7] §cHarcba kerültél, a teleportálás megszakadt!");
                                hashMap.remove(p.getUniqueId());
                            }
                            i++;
                            hashMap.put(p.getUniqueId(), i);
                        } else {
                            p.sendMessage("§7[§6Teleport§7] §cMegmozdultál így a teleportálás megszakadt!");
                            hashMap.remove(p.getUniqueId());
                            cancel();
                            return;
                        }
                    }
                }.runTaskTimer(m, 0L, 1L);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(hashMap == null || hashMap.isEmpty()) return;
        if(hashMap.containsKey(e.getPlayer().getUniqueId())) {
            if (!e.getFrom().getBlock().getLocation().equals(e.getTo().getBlock().getLocation())) {
                hashMap.remove(e.getPlayer().getUniqueId());
            }
        }
    }
}
