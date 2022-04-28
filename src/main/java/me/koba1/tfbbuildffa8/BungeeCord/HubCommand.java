package me.koba1.tfbbuildffa8.BungeeCord;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class HubCommand implements Listener, CommandExecutor {

    private Main m = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("hub")) {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    sendServer((Player) sender, "lobby");
                    sender.sendMessage("§cConnecting to the lobby!");
                }
            }
        }
        return false;
    }

    public void sendServer(final Player p, final String server) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            dataOutputStream.writeUTF("ConnectOther");
            dataOutputStream.writeUTF(p.getName());
            dataOutputStream.writeUTF(server);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (dataOutputStream != null) {
            m.getServer().sendPluginMessage(m, "BungeeCord", byteArrayOutputStream.toByteArray());
        }
    }
}
