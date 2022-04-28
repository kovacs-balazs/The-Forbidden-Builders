package me.koba1.tfbbuildffa8.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemLoseEvent implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(!e.getPlayer().hasPermission("buildffa.dropitem.bypass")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChange1(InventoryInteractEvent e) {
        if(e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getInventory().equals(e.getView().getBottomInventory())) {
                if(!e.getWhoClicked().hasPermission("buildffa.inventory.bypass")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onChange2(InventoryClickEvent e) {
        if(e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getView().getBottomInventory() != null) {
                try {
                    if (e.getClickedInventory().equals(e.getView().getBottomInventory())) {
                        if (!e.getWhoClicked().hasPermission("buildffa.inventory.bypass")) {
                            e.setCancelled(true);
                            if (e.getClick() == ClickType.NUMBER_KEY) {
                                e.setCancelled(true);
                            }
                        }
                    }
                } catch (NullPointerException ex) {

                }
            } else
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChange3(InventoryDragEvent e) {
        if(e.getWhoClicked().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getInventory().equals(e.getView().getBottomInventory())) {
                if (!e.getWhoClicked().hasPermission("buildffa.inventory.bypass")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
