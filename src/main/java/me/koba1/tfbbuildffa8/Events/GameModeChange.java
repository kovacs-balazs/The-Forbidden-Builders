package me.koba1.tfbbuildffa8.Events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GameModeChange implements Listener {

    @EventHandler
    public void onChange(PlayerGameModeChangeEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getNewGameMode() != GameMode.SURVIVAL) {
                if(!e.getPlayer().hasPermission("buildffa.gamemode.bypass")) {
                    e.getPlayer().sendMessage("§cEbben a világban nem válthatsz játékmódot!");
                    e.setCancelled(true);
                }
            }
        }
    }
}
