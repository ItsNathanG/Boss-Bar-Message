package me.itsnathang.bossbarmessage.config;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {
    private YamlConfiguration config;
    private final BossBarMessage plugin;

    // Try to load configuration files.
    public ConfigManager(BossBarMessage plugin) {
        this.plugin = plugin;

        reloadConfig();
    }

    public void reloadConfig() {
        File conf = new File(plugin.getDataFolder() + File.separator + "config.yml");

        if(!conf.exists())
            plugin.saveResource("config.yml", true);

        config = YamlConfiguration.loadConfiguration(conf);
    }

    public String getDefault(String key, String def) {
        return config.getString(key, def);
    }

}
