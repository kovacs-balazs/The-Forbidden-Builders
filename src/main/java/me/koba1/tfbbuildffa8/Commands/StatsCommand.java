package me.koba1.tfbbuildffa8.Commands;

import com.sun.istack.internal.NotNull;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class StatsCommand implements CommandExecutor {

    public static FileConfiguration getData() {
        return DataStorage.getData();
    }
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("stats")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 0) {
                    double kill = getData().getInt("Players." + p.getUniqueId() + ".kills");
                    int balance = getData().getInt("Players." + p.getUniqueId() + ".balance");
                    double death = getData().getInt("Players." + p.getUniqueId() + ".deaths");
                    int currentStreakKiller = getData().getInt("Players." + p.getUniqueId() + ".streak");

                    double kdr = (kill / death);

                    sender.sendMessage(" ");
                    sender.sendMessage("§7- §eYour stats:");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7┌ §eKills: §6§o" + (int) kill);
                    sender.sendMessage("§7├ §eDeaths: §6§o" + (int) death);
                    sender.sendMessage("§7├ §eStreak: §6§o" + currentStreakKiller);
                    sender.sendMessage("§7└ §eKDR: §6§o" + df.format(kdr));
                    sender.sendMessage(" ");
                    sender.sendMessage("§7✯ §eBalance: §6✯§6§o" + balance);
                    sender.sendMessage(" ");
                }
            }
        }
        return false;
    }
}
