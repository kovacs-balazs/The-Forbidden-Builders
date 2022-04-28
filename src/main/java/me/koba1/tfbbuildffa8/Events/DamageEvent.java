package me.koba1.tfbbuildffa8.Events;

import me.koba1.tfbbuildffa8.API.FFAUltimateAPI;
import me.koba1.tfbbuildffa8.Files.Ultimates;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity().getWorld().getName().equalsIgnoreCase("buildffa")
                && e.getDamager().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(e.getEntity() instanceof Player) {
                if(e.getDamager() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    Player damager = (Player) e.getDamager();

                    if (damager.getItemInHand().getType() == Material.WOOD_SWORD) {
                        if(damager.isSneaking()) {
                            if(!Main.containsUltimate(damager.getUniqueId())) {
                                p.setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(8));
                                Ultimates.getUltimate().set("Ultimates.Ultimate1." + damager.getUniqueId(), ((System.currentTimeMillis() / 1000) + 120));
                                Ultimates.saveUltimate();
                                FFAUltimateAPI.createBossbar(damager);
                                FFAUltimateAPI.ultimate1Timer(damager);
                            }
                        }
                        for(PotionEffect effect : p.getActivePotionEffects()) {
                            if(effect.getType().getName() == PotionEffectType.REGENERATION.getName()) {
                                if(effect.getAmplifier() == 1) {
                                    if(ThreadLocalRandom.current().nextInt(0, 99) > 49) {
                                        e.setDamage(EntityDamageEvent.DamageModifier.BASE, 0D);
                                        return;
                                    }
                                }
                            }
                            else if(effect.getType().getName() == PotionEffectType.ABSORPTION.getName()) {
                                if(ThreadLocalRandom.current().nextInt(0, 99) < 15) {
                                    e.setDamage(EntityDamageEvent.DamageModifier.BASE, 0D);
                                    return;
                                }
                            }
                        }
                        e.setDamage(EntityDamageEvent.DamageModifier.BASE, 2.5D);
                    }
                    //
                    else if (damager.getItemInHand().getType() == Material.STICK) {
                        e.setDamage(EntityDamageEvent.DamageModifier.BASE, 0D);
                        if(damager.isSneaking()) {
                            if(!Main.containsUltimate(damager.getUniqueId())) {
                                p.setVelocity(damager.getLocation().getDirection().setY(0).normalize().multiply(8));
                                Ultimates.getUltimate().set("Ultimates.Ultimate1." + damager.getUniqueId(), ((System.currentTimeMillis() / 1000) + 120));
                                Ultimates.saveUltimate();
                                FFAUltimateAPI.createBossbar(damager);
                                FFAUltimateAPI.ultimate1Timer(damager);
                            }
                        }
                    }
                }
            }
        }
    }
}
