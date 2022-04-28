package me.koba1.tfbbuildffa8.Files;

import me.koba1.tfbbuildffa8.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataStorage {

    private static File playerdatacfg;
    private static FileConfiguration playerdatafile;

    public static Main m = Main.getPlugin(Main.class);

    public static void setup() {
        playerdatacfg = new File(m.getDataFolder(), "playerdata.yml"); //
        if(!playerdatacfg.exists()) {
            try {
                playerdatacfg.createNewFile();
            } catch (IOException e) {
                // owww
            }
        }
        playerdatafile = YamlConfiguration.loadConfiguration(playerdatacfg);
    }

    public static FileConfiguration getData() {
        return playerdatafile;
    }

    public static void saveData() {
        //File file = new File(m.getDataFolder(), "playerdata.yml");
        //YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            playerdatafile.save(playerdatacfg);
        } catch (IOException e) {
            System.out.println("Can't save language file");
        }
    }

    public static void relaodData() {
        playerdatafile = YamlConfiguration.loadConfiguration(playerdatacfg);
    }
}
