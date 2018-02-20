package me.itsnathang.bossbarmessage;

import me.itsnathang.bossbarmessage.commands.CommandHandler;
import me.itsnathang.bossbarmessage.util.BossBarHandler;
import me.itsnathang.bossbarmessage.config.ConfigManager;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBarMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("bossbarmessage").setExecutor(new CommandHandler(this));

        // Initialize Configuration Manager
        new ConfigManager(this);

        new BossBarHandler(this);

        // bStats
        new MetricsLite(this);
    }

}
