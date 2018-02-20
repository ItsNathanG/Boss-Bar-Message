package me.itsnathang.bossbarmessage.util;

import me.itsnathang.bossbarmessage.BossBarMessage;
import me.itsnathang.bossbarmessage.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarHandler {
    private static BossBarMessage plugin;

    public BossBarHandler(BossBarMessage p) {
        plugin = p;
    }

    // Send boss bar to an individual.
    public static BossBar sendBar(String message, Player player, BarColor color) {
        BossBar bar = Bukkit.createBossBar(message, color, BarStyle.SOLID);

        //bar.setTitle(ConfigManager.getMessage(name));
        //bar.setColor(ConfigManager.getColor(name));

        bar.addPlayer(player);

        return bar;
    }

    // Send timed bar.
    public static BossBar sendBar(Player player, BarColor color, BarStyle style, int time, String message) {
        BossBar bar = Bukkit.createBossBar(message, color, style);

        bar.addPlayer(player);

        Runnable remove_bar = bar::removeAll;

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, remove_bar, time * 20);

        return bar;
    }

    // Send boss bar to everyone one server.
    public static void sendGlobal(String name) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            //sendBar(name, player);
        }
    }



}
