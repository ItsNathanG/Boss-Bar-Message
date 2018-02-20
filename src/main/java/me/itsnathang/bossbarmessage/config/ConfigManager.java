package me.itsnathang.bossbarmessage.config;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.boss.BarColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static YamlConfiguration config;
    private static BossBarMessage plugin;

    // Try to load configuration files.
    public ConfigManager(BossBarMessage plugin) {
        ConfigManager.plugin = plugin;

        new LanguageManager(plugin);

        reloadConfig();
    }

    public static void reloadConfig() {
        File conf = new File(plugin.getDataFolder() + File.separator + "config.yml");

        if(!conf.exists())
            plugin.saveResource("config.yml", true);

        config = YamlConfiguration.loadConfiguration(conf);
    }

    public static String getDefault(String key) {
        return config.getString(key);
    }

    public static String getDefault(String key, String def) {
        return config.getString(key, def);
    }

}
