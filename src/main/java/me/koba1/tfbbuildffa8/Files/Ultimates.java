package me.koba1.tfbbuildffa8.Files;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Ultimates {

    private static File ultimatecfg;
    private static FileConfiguration ultimatefile;

    public static Main m = Main.getPlugin(Main.class);

    public static void setup() {
        ultimatecfg = new File(m.getDataFolder(), "ultimates.yml"); //
        if(!ultimatecfg.exists()) {
            try {
                ultimatecfg.createNewFile();
            } catch (IOException e) {
                // owww
            }
        }
        ultimatefile = YamlConfiguration.loadConfiguration(ultimatecfg);
    }

    public static FileConfiguration getUltimate() {
        return ultimatefile;
    }

    public static void saveUltimate() {
        //File file = new File(m.getDataFolder(), "playerdata.yml");
        //YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            ultimatefile.save(ultimatecfg);
        } catch (IOException e) {
            System.out.println("Can't save language file");
        }
    }

    public static void reloadUltimate() {
        ultimatefile = YamlConfiguration.loadConfiguration(ultimatecfg);
    }
}
