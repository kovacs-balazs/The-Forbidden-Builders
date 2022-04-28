package me.koba1.tfbbuildffa8;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.sun.istack.internal.NotNull;
import me.koba1.tfbbuildffa8.API.FFALeaderboardAPI;
import me.koba1.tfbbuildffa8.BungeeCord.HubCommand;
import me.koba1.tfbbuildffa8.Commands.MainCommand;
import me.koba1.tfbbuildffa8.Commands.SpawnCommand;
import me.koba1.tfbbuildffa8.Commands.StatsCommand;
import me.koba1.tfbbuildffa8.Events.*;
import me.koba1.tfbbuildffa8.Events.PVP.DeathEvent;
import me.koba1.tfbbuildffa8.Events.PVP.MoveEvent;
import me.koba1.tfbbuildffa8.Events.PVP.PvPTimerEvent;
import me.koba1.tfbbuildffa8.Events.PVP.QuitEvent;
import me.koba1.tfbbuildffa8.Files.DataStorage;
import me.koba1.tfbbuildffa8.Files.Ultimates;
import me.koba1.tfbbuildffa8.Gadets.PlatformEvent;
import me.koba1.tfbbuildffa8.Gadets.SlimeEvent;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Item.InventoryItemEvent;
import me.koba1.tfbbuildffa8.ItemSlotSelection.Number.NumberEvent;
import me.koba1.tfbbuildffa8.Items.ItemList;
import me.koba1.tfbbuildffa8.Shop.Events.ShopEvent;
import me.neznamy.tab.api.TabAPI;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements PluginMessageListener {

    public static HashMap<String, Long> pvpTimer;
    public static HashMap<UUID, Long> pvpTimerTimer;
    public static HashMap<ItemStack, Integer> itemPrices = new HashMap<>();
    public static HashMap<UUID, BossBar> bossBars;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEnable() {
        // Plugin startup logic

        bossBars = new HashMap<UUID, BossBar>();

        itemPrices.put(ItemList.slime(), 16);
        itemPrices.put(ItemList.goldenapple(), 8);
        itemPrices.put(ItemList.platform(), 12);
        itemPrices.put(ItemList.enderpearl(), 4);

        pvpTimer = new HashMap<>();
        pvpTimerTimer = new HashMap<>();

//        try {
////            new SQL().connect();
////            new SQLFunctions().createTable();
////            new SQLFunctions().createServer("buildffa");
////            new SQLFunctions().setStatus("buildffa", "online");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        DataStorage.setup();
        Ultimates.setup();
        DataStorage.getData().addDefault("1", "2");
        DataStorage.saveData();

        Ultimates.getUltimate().addDefault("1", "2");
        Ultimates.saveUltimate();


        saveDefaultConfig();
        saveConfig();

        Ultimates.getUltimate().set("Ultimates", null);
        Ultimates.saveUltimate();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        /*for(Player p : Bukkit.getOnlinePlayers()) {
            Ultimates.getUltimate().set("Ultimates.Ultimate1." + p.getUniqueId(), ((System.currentTimeMillis() / 1000) + 120));
            Ultimates.saveUltimate();
            FFAUltimateAPI.createBossbar(p);
            FFAUltimateAPI.ultimate1Timer(p);
        }*/

        //LBArmors.spawnArmorStand(Bukkit.getWorld("buildffa"));
        //LBArmors.editArmorStand(Bukkit.getWorld("buildffa"));

        PvPTimerEvent.pvpTimer();
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new NumberEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryItemEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinWorldEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        getServer().getPluginManager().registerEvents(new PvPTimerEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getServer().getPluginManager().registerEvents(new JumpPadInteract(), this);
        getServer().getPluginManager().registerEvents(new ShopEvent(), this);
        getServer().getPluginManager().registerEvents(new SlimeEvent(), this);
        getServer().getPluginManager().registerEvents(new PlatformEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemLoseEvent(), this);
        getServer().getPluginManager().registerEvents(new SpawnCommand(), this);
        getServer().getPluginManager().registerEvents(new GameModeChange(), this);

        getCommand("slotselector").setExecutor(new MainCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("shop").setExecutor(new MainCommand());
        getCommand("buildffa").setExecutor(new MainCommand());
        getCommand("spawn").setExecutor(new MainCommand());
        getCommand("hub").setExecutor(new HubCommand());

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%kills%", 1000, tabPlayer ->
                DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".kills"));

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%deaths%", 1000, tabPlayer ->
                DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".deaths"));

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%streak%", 1000, tabPlayer ->
                DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".streak"));

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%balance%", 1000, tabPlayer ->
                DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".balance"));

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%killleaderboard%", 1000, tabPlayer ->
                FFALeaderboardAPI.getLeaderboard().get(tabPlayer.getUniqueId()));

        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%kdr%", 1000, tabPlayer -> {
            double kill = DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".kills");
            double death = DataStorage.getData().getInt("Players." + tabPlayer.getUniqueId() + ".deaths");

            double kdr = (kill / death);

            return df.format(kdr);
        });
    }

    @Override
    public void onDisable() {
        //new SQLFunctions().setStatus("buildffa", "offline");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        for(Player p : Bukkit.getOnlinePlayers()) {
            //FFAUltimateAPI.removePlayer(p);
        }
    }

    public static boolean containsUltimate(UUID uuid) {
        try {
            for (String str : Ultimates.getUltimate().getConfigurationSection("Ultimates").getKeys(false)) {
                for (String str2 : Ultimates.getUltimate().getConfigurationSection("Ultimates." + str).getKeys(false)) {
                    if (str2.equalsIgnoreCase(uuid.toString()))
                        return true;
                }
            }
        } catch (NullPointerException ex) {
            return false;
        }
        return false;
    }

   @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }
}
