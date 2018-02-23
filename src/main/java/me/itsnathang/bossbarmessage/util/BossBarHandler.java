package me.itsnathang.bossbarmessage.util;

import me.clip.placeholderapi.PlaceholderAPI;
import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarHandler {
    private final BossBarMessage plugin;
    private final Boolean PLACEHOLDER_API;

    public BossBarHandler(BossBarMessage plugin) {
        this.plugin = plugin;

        // PlaceholderAPI hook
        PLACEHOLDER_API = plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    // Send timed bar
    public void sendBar(BossBar bar, int time, Player player) {
        if (PLACEHOLDER_API)
            bar.setTitle(PlaceholderAPI.setPlaceholders(player, bar.getTitle()));

        bar.addPlayer(player);

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

    // Send boss bar to everyone one server.
    public void sendGlobal(BossBar bar, int time) {

        Bukkit.getOnlinePlayers().forEach((player) -> {
            if (PLACEHOLDER_API)
                bar.setTitle(PlaceholderAPI.setPlaceholders(player, bar.getTitle()));

            bar.addPlayer(player);
        });

        new BarCountdown(bar, time).runTaskTimer(plugin, 0L, 20L);

        plugin.getServer().getScheduler().runTaskLater(plugin, bar::removeAll, time * 20);
    }

}
