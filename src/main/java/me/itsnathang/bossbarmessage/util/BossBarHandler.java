package me.itsnathang.bossbarmessage.util;

import me.clip.placeholderapi.PlaceholderAPI;
import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarHandler {
    private BossBarMessage plugin;
    private Boolean placeholderApi;

    public BossBarHandler(BossBarMessage plugin) {
        this.plugin = plugin;

        // PlaceholderAPI hook
        placeholderApi = plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    // Send timed bar
    public void sendBar(Player player, BarColor color, BarStyle style, int time, String message) {
        if (placeholderApi)
            message = PlaceholderAPI.setPlaceholders(player, message);

        BossBar bar = Bukkit.createBossBar(message, color, style);

        bar.addPlayer(player);

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

    // Send boss bar to everyone one server.
    public void sendGlobal(BarColor color, BarStyle style, int time, String message) {
        BossBar bar = Bukkit.createBossBar(message, color, style);

        Bukkit.getOnlinePlayers().forEach((player) -> {
            if (placeholderApi)
                bar.setTitle(PlaceholderAPI.setPlaceholders(player, message));

            bar.addPlayer(player);
        });

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

}
