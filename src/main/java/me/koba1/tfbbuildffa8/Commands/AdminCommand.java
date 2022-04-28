package me.koba1.tfbbuildffa8.Commands;

import me.koba1.tfbbuildffa8.API.FFADataApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AdminCommand {

    AdminCommand(Player p, String[] args) {
        // /bffa admin balance add <player> <amount>
        // /bffa admin balance take <player> <amount>
        // /bffa admin balance set <player> <amount> ☠
        // /bffa admin balance get <player> ☠
        if(args.length == 2) {
            if(args[1].equalsIgnoreCase("getitems")) {
                if(p.hasPermission("buildffa.getitems")) {
                    me.koba1.tfbbuildffa8.Events.JoinWorldEvent.addItems(p);
                }
            }
        }
        if(args.length == 4) {
            if(args[1].equalsIgnoreCase("balance")) {
                if(Bukkit.getPlayer(args[3]) != null) {
                    if(args[2].equalsIgnoreCase("get")) {
                        if(p.hasPermission("buildffa.balance.get")) {
                            Player target = Bukkit.getPlayer(args[3]);
                            p.sendMessage("§c§o" + target.getName() + " §7egyenlege: §c§o☠" + FFADataApi.getBalance(target));
                        } else {
                            p.sendMessage("§cEhhez nincs jogod!");
                        }
                    }
                }
            }
        }
        if(args.length == 5) {
            if(args[1].equalsIgnoreCase("balance")) {
                if(Bukkit.getPlayer(args[3]) != null) {
                    if(args[4].matches("^[0-9]+$")) {
                        Player target = Bukkit.getPlayer(args[3]);
                        try {
                            if (args[2].equalsIgnoreCase("set")) {
                                if (p.hasPermission("buildffa.balance.set")) {
                                    if(FFADataApi.setBalance(target, Integer.parseInt(args[4]))) {
                                        p.sendMessage("§c§o" + target.getName() + "§7 egyenlegét beállítottad: §c§o☠" + args[4]);
                                        target.sendMessage("§aEgyenleged be lett állítva: §2§o☠" + args[4]);
                                    } else
                                        p.sendMessage("§cHiba történt. Valószínüleg nem elvégezhető a művelet!");
                                } else
                                    p.sendMessage("§cEhhez nincs jogod!");
                            }
                            //
                            else if (args[2].equalsIgnoreCase("add")) {
                                if (p.hasPermission("buildffa.balance.add")) {
                                    if(FFADataApi.addBalance(target, Integer.parseInt(args[4]))) {
                                        p.sendMessage(
                                                "§c§o" + target.getName() + "§7 egyenlegéhez hozzáadtál §c§o☠" + args[4] + "§7. Jelenlegi egyenlege: §c§o☠" + FFADataApi.getBalance(target));
                                        target.sendMessage("§aEgyenlegedhez hozzáadva §2§o☠" + args[4] + "§a. Jelenlegi egyenleged: §2§o☠" + FFADataApi.getBalance(target));
                                    } else
                                        p.sendMessage("§cHiba történt. Valószínüleg nem elvégezhető a művelet!");
                                } else
                                    p.sendMessage("§cEhhez nincs jogod!");
                            }
                            //
                            else if (args[2].equalsIgnoreCase("take")) {
                                if (p.hasPermission("buildffa.balance.take")) {
                                    if(FFADataApi.takeBalance(target, Integer.parseInt(args[4]))) {
                                        p.sendMessage(
                                                "§c§o" + target.getName() + "§7 egyenlegéből elvettél §c§o☠" + args[4] + "§7. Jelenlegi egyenlege: §c§o☠" + FFADataApi.getBalance(target));
                                        target.sendMessage("§aEgyenlegedből elvéve §2§o☠" + args[4] + "§a. Jelenlegi egyenleged: §2§o☠" + FFADataApi.getBalance(target));
                                    } else
                                        p.sendMessage("§cHiba történt. Valószínüleg nem elvégezhető a művelet!");
                                } else
                                    p.sendMessage("§cEhhez nincs jogod!");
                            }
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cTúl nagy értéket adtál meg!");
                        }
                    } else {
                        p.sendMessage("§cÉrvényes számot adj meg!");
                    }
                } else {
                    p.sendMessage("§cEz a játékos nem elérhető");
                }
            }
        }
    }
}
