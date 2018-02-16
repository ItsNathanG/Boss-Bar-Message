package me.itsnathang.bossbarmessage.util;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.boss.BarColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static YamlConfiguration config, data;
    private static File configFile, dataFile;

    // Try to load configuration files.
    public ConfigManager(BossBarMessage plugin) {
        configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
        dataFile = new File(plugin.getDataFolder() + File.separator + "data.yml");

        if (!configFile.exists())
            plugin.saveResource("config.yml", true);

        if (!dataFile.exists())
            plugin.saveResource("data.yml", true);

        config = YamlConfiguration.loadConfiguration(configFile);
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static boolean saveConfig() {
        try {
            config.save(configFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean saveData() {
        try {
            data.save(dataFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Data File
    public static String getMessage(String name) {
        return data.getString("announcements." + name + ".message");
    }

    public static BarColor getColor(String name) {
        return BarColor.valueOf(data.getString("announcements." + name + ".color"));
    }
}
