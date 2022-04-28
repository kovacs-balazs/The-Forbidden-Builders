package me.koba1.tfbbuildffa8.LeaderboardHolo;

import me.koba1.tfbbuildffa8.API.FFALeaderboardAPI;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class LBArmors {

    private static Main m = Main.getPlugin(Main.class);

    public static void spawnArmorStand(World world) {
        Location loc = new Location(world, -3.25, 115, 4.25);
        for (int i = 0; i < 11; i++) {
            ArmorStand stand = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setGravity(false);
            stand.setCustomName("§eTOP LISTA");
            stand.setCustomNameVisible(true);
            loc.add(0, -0.3, 0);
        }
    }

    public static void editArmorStand(World world) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = new Location(world, -3.25, 114.7, 4.25);
                int i = 0;
                for (Entity stand1 : loc.getWorld().getNearbyEntities(loc, 0, 3, 0)) { // 3
                    if(!(stand1 instanceof ArmorStand)) {
                        return;
                    }
                    ArmorStand stand = (ArmorStand) stand1;
                    if (stand.getCustomName() != null) {
                        if (!stand.getCustomName().equalsIgnoreCase("§6TOP LISTA")) {
                            stand.setVisible(false);
                            stand.setGravity(false);
                            //stand.setInvulnerable(true);
                            //stand.setCanMove(false);
                            for (Map.Entry<UUID, Integer> hash : FFALeaderboardAPI.getLeaderboard().entrySet()) {
                                if (hash.getValue() == i) {
                                    try {
                                        if (Bukkit.getPlayer(hash.getKey()) != null) {
                                            stand.setCustomName("§e#" + (i) + "§f - §b" + Bukkit.getPlayer(hash.getKey()).getName());
                                        } else {
                                            stand.setCustomName("§e#" + (i) + "§f - §b" + Bukkit.getOfflinePlayer(hash.getKey()).getName());
                                        }
                                    } catch (NullPointerException ex) {
                                        stand.setCustomName("§e#" + (i) + "§f - §b" + Bukkit.getOfflinePlayer(hash.getKey()).getName());
                                    }
                                }
                            }

                            stand.setCustomNameVisible(true);
                            loc.add(0, -0.3, 0);
                            i++;
                        }
                    }
                }
            }
        }.runTaskTimer(m, 0L, 20L); // 6000L
    }
}
