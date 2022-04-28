package me.koba1.tfbbuildffa8.API;

import me.koba1.tfbbuildffa8.Files.Ultimates;
import me.koba1.tfbbuildffa8.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FFAUltimateAPI {

    private static Main m = Main.getPlugin(Main.class);

    public static void createBossbar(Player p) {
        long ultimate1 = Ultimates.getUltimate().getLong("Ultimates.Ultimate1." + p.getUniqueId());
        int unix = (int) ((ultimate1) - (System.currentTimeMillis() / 1000));

        BossBar bar = null;// = Bukkit.createBossbar(timeFormat(unix), BarColor.RED, BarStyle.SOLID);

        bar.setProgress(1.0D);
        bar.setVisible(true);
        bar.addPlayer(p);

        Main.bossBars.put(p.getUniqueId(), bar);

        new BukkitRunnable() {
            int sec = (int) ((ultimate1) - (System.currentTimeMillis() / 1000));
            @Override
            public void run() {
                try {
                    sec = (int) ((ultimate1) - (System.currentTimeMillis() / 1000));
                    if(!p.isOnline()) {
                        bar.removeAll();
                        cancel();
                        return;
                    }
                    if(bar.getPlayers().isEmpty()) {
                        bar.removeAll();
                        cancel();
                        return;
                    }
                    bar.setTitle(timeFormat(sec));
                } catch (NullPointerException ex) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(m, 0L, 20L);
    }

    private static String timeFormat(int unix) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        Date date = new Date((long) (unix * 1000));
        return formatter.format(date);
    }

    /*public static void removePlayer(Player p) {
        try {
            BossBar bar = Main.bossBars.get(p.getUniqueId());
            bar.removePlayer(p);
            Main.bossBars.put(p.getUniqueId(), bar);
        } catch (NullPointerException ex) {

        }

    }*/

    public static void ultimate1Timer(Player p) {
        UUID uuid = p.getUniqueId();
        new BukkitRunnable() {
            @Override
            public void run() {
                if(Main.containsUltimate(p.getUniqueId())) {
                    try {
                        Player p2 = Bukkit.getPlayer(uuid);
                        p2.sendMessage("§8[§aAbility§8] §aÚjra tudod használni a §2§oHigher Knockback§a képességedet.");
                        Ultimates.getUltimate().set("Ultimates.Ultimate1." + p.getUniqueId(), null);
                        Ultimates.saveUltimate();
                        //removePlayer(p2);
                        Main.bossBars.remove(p.getUniqueId());
                    } catch (NullPointerException ex) {
                        Ultimates.getUltimate().set("Ultimates.Ultimate1." + p.getUniqueId(), null);
                        Ultimates.saveUltimate();
                    }
                }
            }
        }.runTaskLater(m, 2420L);
    }
}
