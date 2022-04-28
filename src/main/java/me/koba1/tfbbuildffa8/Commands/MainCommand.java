package me.koba1.tfbbuildffa8.Commands;

import com.sun.istack.internal.NotNull;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Item.ItemInventory;
import me.koba1.tfbbuildffa8.Shop.ShopGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("slotselector")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.getWorld().getName().equalsIgnoreCase("buildffa")) {
                    p.openInventory(ItemInventory.inv());
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("shop")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.getWorld().getName().equalsIgnoreCase("buildffa")) {
                    p.openInventory(ShopGUI.inv(p));
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("buildffa")) {
            if(sender instanceof Player) {
                if(args.length > 0) {
                    if(args[0].equalsIgnoreCase("admin")) {
                        new AdminCommand(((Player) sender), args);
                    }
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("spawn")) {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    new SpawnCommand().SpawnCommand(((Player) sender));
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("buildffa")) {
            if(args.length == 1) {
                if (sender.hasPermission("buildffa.balance")) {
                    return Arrays.asList("admin");
                }
            }

            else if(args.length == 2) {
                if(sender.hasPermission("buildffa.getitems") && sender.hasPermission("buildffa.balance")) {
                    return Arrays.asList("balance", "getitems");
                }
                else if(sender.hasPermission("buildffa.balance")) {
                    return Arrays.asList("balance");
                }
                else if(sender.hasPermission("buildffa.getitems")) {
                    return Arrays.asList("getitems");
                }
            }

            else if(args.length == 3) {
                if(sender.hasPermission("buildffa.balance")) {
                    return Arrays.asList("get", "set", "add", "take");
                }
            }

            else if(args.length == 5) {
                if(sender.hasPermission("buildffa.balance")) {
                    if(args[2] != "get") {
                        return Arrays.asList("<összeg>");
                    }
                }
            }
        }
        return null;
    }
}
