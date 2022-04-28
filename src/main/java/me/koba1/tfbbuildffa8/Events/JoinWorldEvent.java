package me.koba1.tfbbuildffa8.Events;

import me.koba1.tfbbuildffa8.API.FFAUltimateAPI;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinWorldEvent implements Listener {

    private static Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerChangedWorldEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            Location loc = new Location(e.getPlayer().getWorld(), 0.5, 121, -1.5, 0, 0);
            e.getPlayer().teleport(loc);
            e.getPlayer().setHealth(20D);
            e.getPlayer().setSaturation(20F);
            if(!m.getConfig().contains("ItemSlot." + e.getPlayer().getUniqueId() + "." + "STICK")) {
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "STICK", 0);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "WOOL", 2);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "GOLDEN_APPLE", 6);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "STONE_PLATE", 4);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "ENDER_PEARL", 5);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "SLIME_BLOCK", 3);
                m.getConfig().set("ItemSlot." + e.getPlayer().getUniqueId() + "." + "WOOD_SWORD", 1);
                m.saveConfig();
                addItems(e.getPlayer());
            } else {
                addItems(e.getPlayer());
            }
            if(Main.containsUltimate(e.getPlayer().getUniqueId())) {
                FFAUltimateAPI.createBossbar(e.getPlayer());
            }
        }
        if(!e.getPlayer().getWorld().getName().equalsIgnoreCase("buildffa")) {
            if(Main.bossBars.containsKey(e.getPlayer().getUniqueId())) {
                //FFAUltimateAPI.removePlayer(e.getPlayer());
            }
        }
    }

    public static void addItems(Player p) {
        for(String str : m.getConfig().getConfigurationSection("ItemSlot." + p.getUniqueId()).getKeys(false)) {
            //if(p.getInventory().contains(whichItem(str).getType()))
                p.getInventory().setItem(m.getConfig().getInt("ItemSlot." + p.getUniqueId() + "." + str), whichItem(str));
        }
    }

    public static ItemStack whichItem(String name) {
        switch (name) {
            case "STICK":
                return ItemList.stick();
            case "WOOL":
                return ItemList.wool();
            case "ENDER_PEARL":
                return ItemList.enderpearl();
            case "WOOD_SWORD":
                return ItemList.kard();
           /* case "STONE_PLATE":
                return ItemList.platform();
            case "SLIME":
                return ItemList.slime();
            case "GOLDEN_APPLE":
                return ItemList.goldenapple();*/
        }
        return null;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getPluginManager().callEvent(new PlayerChangedWorldEvent(e.getPlayer(), e.getPlayer().getWorld()));
    }
}
