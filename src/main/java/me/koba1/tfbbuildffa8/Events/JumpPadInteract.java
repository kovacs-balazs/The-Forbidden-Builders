package me.koba1.tfbbuildffa8.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;


public class JumpPadInteract implements Listener {

    @EventHandler
    public void onPressurePlateInteract(PlayerInteractEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getAction() == Action.PHYSICAL) {
                if(e.getClickedBlock() != null) {
                    if(e.getClickedBlock().getType() != null) {
                        if(e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                            Player p = e.getPlayer();
                            try {
                                Vector v = p.getLocation().getDirection().multiply(9D).setY(4D);
                                p.setVelocity(v);
                                //p.setVelocity(p.getLocation().getDirection().multiply(7).setY(3));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
