package me.itsnathang.bossbarmessage.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class BossBarHandler {

    // Send boss bar to an individual.
    public static void sendBar(String name, Player player) {
        BossBar bar = Bukkit.createBossBar("", BarColor.BLUE, BarStyle.SOLID);

        bar.setTitle(ConfigManager.getMessage(name));
        bar.setColor(ConfigManager.getColor(name));

        bar.addPlayer(player);
    }

    // Send timed bar.
    public static void sendBar(String name, Player player, int time) {
        Runnable boss_bar = () -> sendBar(name, player);

        BukkitTask.runTask(getPlugin(), boss_bar);
    }

    // Send boss bar to everyone one server.
    public static void sendGlobal(String name) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendBar(name, player);
        }
    }



}
