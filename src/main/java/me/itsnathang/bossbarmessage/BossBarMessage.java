package me.itsnathang.bossbarmessage;

import me.itsnathang.bossbarmessage.commands.CommandHandler;
import me.itsnathang.bossbarmessage.config.LanguageManager;
import me.itsnathang.bossbarmessage.util.BossBarHandler;
import me.itsnathang.bossbarmessage.config.ConfigManager;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBarMessage extends JavaPlugin {
    private ConfigManager config;
    private LanguageManager language;

    @Override
    public void onEnable() {
        getCommand("bossbarmessage").setExecutor(new CommandHandler(this));

        // Initialize Configuration Manager
        config = new ConfigManager(this);
        language = new LanguageManager(this);

        new BossBarHandler(this);

        // bStats
        new MetricsLite(this);
    }

    public ConfigManager getConfigManager() {
        return config;
    }

    public LanguageManager getLanguage() {
        return language;
    }
}
