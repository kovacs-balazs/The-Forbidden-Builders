package me.koba1.tfbbuildffa8.Commands;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Preprocess implements Listener {

    @EventHandler
    public void onPreprocess(PlayerCommandPreprocessEvent e) {
        if(Main.pvpTimerTimer.containsKey(e.getPlayer().getUniqueId())) {
            if(e.getMessage().equalsIgnoreCase("/bolt") || e.getMessage().equalsIgnoreCase("/shop")) return;
            if(e.getPlayer().hasPermission("buildffa.pvpcmd.bypass")) return;
            e.setCancelled(true);
            e.getPlayer().sendMessage("§7[§cHarc§7] §cHarc közben nem használhatsz parancsokat!");
        }
    }
}
