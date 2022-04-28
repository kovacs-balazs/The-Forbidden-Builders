package me.koba1.tfbbuildffa8.Gadets;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlatformEvent  implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand() != null) {
                if (e.getPlayer().getItemInHand().getType() != Material.AIR) {
                    if (e.getPlayer().getItemInHand().hasItemMeta()) {
                        if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) return;
                        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lPlatform")) {
                            if (e.getPlayer().getLocation().getBlockY() < 93) {
                                Location loc = e.getPlayer().getLocation();
                                int xMax = loc.getBlockX() + 1;
                                int zMax = loc.getBlockZ() + 1;

                                int xMin = loc.getBlockX() - 1;
                                int zMin = loc.getBlockZ() - 1;


                                boolean activated = false;
                                for (int x = xMin; x <= xMax; x++) {
                                    for (int z = zMin; z <= zMax; z++) {

                                        Location loc2 = new Location(e.getPlayer().getWorld(), x, e.getPlayer().getLocation().getBlockY() - 1, z);
                                        if (loc2.getBlock().getType() != Material.AIR) {
                                        } else {
                                            loc2.getBlock().setType(Material.WOOL);
                                            activated = true;
                                        }
                                    }
                                }
                                if (!activated) {
                                    e.getPlayer().sendMessage("§7[§6§lPlatform§7] §cEzt itt nem aktiválhatod el!");
                                    return;
                                }

                                ItemStack newitem = e.getPlayer().getItemInHand();
                                newitem.setAmount(newitem.getAmount() - 1);
                                e.getPlayer().setItemInHand(newitem);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        for (int x = xMin; x <= xMax; x++) {
                                            for (int z = zMin; z <= zMax; z++) {
                                                Location loc2 = new Location(e.getPlayer().getWorld(), x, loc.getBlockY() - 1, z);
                                                if (loc2.getBlock().getType() == Material.WOOL) {
                                                    loc2.getBlock().setType(Material.AIR);
                                                }
                                            }
                                        }
                                    }
                                }.runTaskLater(m, 100L);
                            } else {
                                e.getPlayer().sendMessage("§7[§6§lPlatform§7] §cEzt itt nem aktiválhatod el!");
                            }
                        }
                    }
                }
            }
        }
    }
}
