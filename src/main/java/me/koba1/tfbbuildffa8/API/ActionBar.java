package me.koba1.tfbbuildffa8.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionBar {

    public static String getActionBar(int sec) {
        switch (sec) {
            case 1:
                return "§7[§a▌§7▌▌▌▌▌▌▌▌▌] -- §a§n" + sec;
            case 2:
                return "§7[§a▌▌§7▌▌▌▌▌▌▌▌] -- §a§n" + sec;
            case 3:
                return "§7[§a▌▌▌§7▌▌▌▌▌▌▌] -- §a§n" + sec;
            case 4:
                return "§7[§a▌▌▌▌§7▌▌▌▌▌▌] -- §a§n" + sec;
            case 5:
                return "§7[§a▌▌▌▌▌§7▌▌▌▌▌] -- §a§n" + sec;
            case 6:
                return "§7[§a▌▌▌▌▌▌§7▌▌▌▌] -- §a§n" + sec;
            case 7:
                return "§7[§a▌▌▌▌▌▌▌§7▌▌▌] -- §a§n" + sec;
            case 8:
                return "§7[§a▌▌▌▌▌▌▌▌§7▌▌] -- §a§n" + sec;
            case 9:
                return "§7[§a▌▌▌▌▌▌▌▌▌§7▌] -- §a§n" + sec;
            case 10:
                return "§7[§a▌▌▌▌▌▌▌▌▌▌§7] -- §a§n" + sec;
            default:
                return " ";
        }
    }

    public static void sendActionbar(Player player, String message) {
        if (player == null || message == null) return;
        String nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);

        //1.10 and up
        if (!nmsVersion.startsWith("v1_9_R") && !nmsVersion.startsWith("v1_8_R")) {
            //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
            return;
        }

        //1.8.x and 1.9.x
        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);

            Class<?> ppoc = Class.forName("net.minecraft.server." + nmsVersion + ".PacketPlayOutChat");
            Class<?> packet = Class.forName("net.minecraft.server." + nmsVersion + ".Packet");
            Object packetPlayOutChat;
            Class<?> chat = Class.forName("net.minecraft.server." + nmsVersion + (nmsVersion.equalsIgnoreCase("v1_8_R1") ? ".ChatSerializer" : ".ChatComponentText"));
            Class<?> chatBaseComponent = Class.forName("net.minecraft.server." + nmsVersion + ".IChatBaseComponent");

            Method method = null;
            if (nmsVersion.equalsIgnoreCase("v1_8_R1")) method = chat.getDeclaredMethod("a", String.class);

            Object object = nmsVersion.equalsIgnoreCase("v1_8_R1") ? chatBaseComponent.cast(method.invoke(chat, "{'text': '" + message + "'}")) : chat.getConstructor(new Class[]{String.class}).newInstance(message);
            packetPlayOutChat = ppoc.getConstructor(new Class[]{chatBaseComponent, Byte.TYPE}).newInstance(object, (byte) 2);

            Method handle = craftPlayerClass.getDeclaredMethod("getHandle");
            Object iCraftPlayer = handle.invoke(craftPlayer);
            Field playerConnectionField = iCraftPlayer.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(iCraftPlayer);
            Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", packet);
            sendPacket.invoke(playerConnection, packetPlayOutChat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
