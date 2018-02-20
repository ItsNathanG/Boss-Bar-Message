package me.itsnathang.bossbarmessage.util;

import me.clip.placeholderapi.PlaceholderAPI;
import me.itsnathang.bossbarmessage.BossBarMessage;
import me.itsnathang.bossbarmessage.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarHandler {
    private static BossBarMessage plugin;
    private static Boolean placeholder_api;

    public BossBarHandler(BossBarMessage plugin) {
        BossBarHandler.plugin = plugin;

        // PlaceholderAPI hook
        placeholder_api = plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    // Send timed bar
    public static void sendBar(Player player, BarColor color, BarStyle style, int time, String message) {
        if (placeholder_api)
            message = PlaceholderAPI.setPlaceholders(player, message);

        BossBar bar = Bukkit.createBossBar(message, color, style);

        bar.addPlayer(player);

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

    // Send boss bar to everyone one server.
    public static void sendGlobal(BarColor color, BarStyle style, int time, String message) {
        BossBar bar = Bukkit.createBossBar(message, color, style);

        Bukkit.getOnlinePlayers().forEach((player) -> {
            if (placeholder_api)
                bar.setTitle(PlaceholderAPI.setPlaceholders(player, message));

            bar.addPlayer(player);
        });

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

}
