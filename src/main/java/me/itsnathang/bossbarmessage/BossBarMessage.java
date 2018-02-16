package me.itsnathang.bossbarmessage;

import me.itsnathang.bossbarmessage.commands.CommandHandler;
import me.itsnathang.bossbarmessage.util.ConfigManager;
import me.itsnathang.bossbarmessage.util.HeadIcon;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

public class BossBarMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("Hello world!");

        HeadIcon.init(this);

        getCommand("bossbarmessage").setExecutor(new CommandHandler());

        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), this);
        // Initialize Configuration Manager
        new ConfigManager(this);
    }

}
