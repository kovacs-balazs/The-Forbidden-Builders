package me.koba1.tfbbuildffa8.Events;

import me.koba1.tfbbuildffa8.API.FFAApi;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockPlace implements Listener {

    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e) {
        if(Bukkit.getWorld("buildffa") != null) {
            if (e.getPlayer().getWorld().getName().toLowerCase().equals("buildffa")) {
                if (!e.isCancelled()) {
                    if (e.getBlock().getType() == Material.WOOL && e.getBlock().getData() == 0) {
                        //ItemStack is = e.getPlayer().getItemInHand();
                        //is.setAmount(64);
                        //e.getPlayer().setItemInHand(is);
                        Location loc = e.getBlock().getLocation();
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Block block = loc.getWorld().getBlockAt(loc);
                                if(block != null) {
                                    if(block.getType() == Material.WOOL && block.getData() == 14) {
                                        block.setType(Material.AIR);
                                        ItemStack is = ItemList.wool();
                                        is.setAmount(1);
                                        FFAApi.addItem(e.getPlayer(), is);
                                        cancel();
                                        return;
                                    }
                                    if(block.getType() == Material.WOOL && block.getData() == 1) {
                                        block.setTypeIdAndData(35, (byte) 14, true); // red
                                    }
                                    if(block.getType() == Material.WOOL && block.getData() == 0) {
                                        block.setTypeIdAndData(35, (byte) 1, true); // orange
                                    }
                                } else {
                                    cancel();
                                    return;
                                }
                            }
                        }.runTaskTimer(m, 40L, 40L);
                    } else if(e.getBlock().getType() == Material.STONE_PLATE) {
                        if(!e.getPlayer().hasPermission("buildffa.placeblock")) {
                            e.setCancelled(true);
                        }
                    } else if(e.getBlock().getType() == Material.SLIME_BLOCK) {
                        if(!e.getPlayer().hasPermission("buildffa.placeblock")) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
