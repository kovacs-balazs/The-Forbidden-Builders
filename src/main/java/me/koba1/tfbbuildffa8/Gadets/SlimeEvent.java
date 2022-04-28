package me.koba1.tfbbuildffa8.Gadets;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SlimeEvent implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getItemInHand() != null) {
                if (e.getPlayer().getItemInHand().getType() != Material.AIR) {
                    if(e.getPlayer().getItemInHand().hasItemMeta()) {
                        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lSlimy jump")) {
                            if(e.getPlayer().getLocation().getBlockY() < 93) {
                                Location loc = e.getPlayer().getLocation();
                                if (loc.getWorld().getBlockAt((int) loc.getX(), (int) loc.getY() - 1, (int) loc.getZ()) != null
                                        && loc.getWorld().getBlockAt((int) loc.getX(), (int) loc.getY() - 1, (int) loc.getZ()).getType() != Material.AIR) {
                                    e.getPlayer().sendMessage("§7[§b§lSlimy Jump§7] §cEzt itt nem aktiválhatod el!");
                                    return;
                                } else {
                                    Block block = e.getPlayer().getLocation().add(0, -1, 0).getBlock();
                                    block.setType(Material.SLIME_BLOCK);
                                    e.getPlayer().teleport(loc);
                                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(0.2).setY(2));

                                    ItemStack newitem = e.getPlayer().getItemInHand();
                                    newitem.setAmount(newitem.getAmount() - 1);
                                    e.getPlayer().setItemInHand(newitem);

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            block.setType(Material.AIR);
                                            cancel();
                                            return;
                                        }
                                    }.runTaskLater(m, 100L);
                                }
                            } else {
                                e.getPlayer().sendMessage("§7[§b§lSlimy Jump§7] §cEzt itt nem aktiválhatod el!");
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            try {
                Location loc = e.getTo();
                if (loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType() == Material.SLIME_BLOCK) {
                    //e.getPlayer().teleport(loc);
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(0.2).setY(2));
                }
            } catch (NullPointerException ex) {

            }
        }
    }
}
