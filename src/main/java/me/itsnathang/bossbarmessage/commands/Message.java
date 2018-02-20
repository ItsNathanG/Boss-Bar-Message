package me.itsnathang.bossbarmessage.commands;

import me.itsnathang.bossbarmessage.config.ConfigManager;
import me.itsnathang.bossbarmessage.util.BossBarHandler;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.itsnathang.bossbarmessage.util.Color.color;
import static me.itsnathang.bossbarmessage.util.Translate.tl;

public class Message {

    public static void sendBossBarMessage(CommandSender sender, String[] args) {
        StringBuilder message = new StringBuilder();
        Player player = null;
        BarColor color = null;
        BarStyle style = null;
        int seconds = -1;

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("player:") | args[i].startsWith("p:")) {

                player = parsePlayer(args[i].replaceAll("(p|player):", ""));

                if (player == null) {
                    sender.sendMessage(tl("player_not_online").replace("%value%", args[i]));
                    return;
                }

            } else if (args[i].startsWith("color:") || args[i].startsWith("c:")) {

                color = parseBarColor(args[i].replaceAll("(c|color):", ""));

                if (color == null) {
                    sender.sendMessage(tl("parse_color").replace("%value%", args[i]));
                    return;
                }

            } else if (args[i].startsWith("seconds:") || args[i].startsWith("s:")) {

                seconds = parseSeconds(args[i].replaceAll("(s|seconds):", ""));

                if (seconds == -1) {
                    sender.sendMessage(tl("parse_seconds").replace("%value%", args[1]));
                    return;
                }
            } else if (args[i].startsWith("permission:") || args[i].startsWith("pe:")) {
                // TODO: Implement Permission
            } else if (args[i].startsWith("type:") || args[i].startsWith("t:")) {
                style = parseBarStyle(args[i].replaceAll("(t|type):", ""));

                if (style == null) {
                    sender.sendMessage(tl("parse_type").replace("%value%", args[1]));
                    return;
                }
            }

            else {
                message.append(color(args[i])).append(" ");
            }
        }

        // TODO: If no player then broadcast message.
        if (player == null)
            player = (Player) sender;

        if (color == null) {
            color = parseBarColor(ConfigManager.getDefault("bar-color", "purple"));
            // Something up with config file if this happens
            if (color == null) color = BarColor.PURPLE;
        }

        if (style == null) {
            style = parseBarStyle(ConfigManager.getDefault("bar-type", "solid"));

            if (style == null) style = BarStyle.SOLID;
        }

        if (seconds == -1) {
            seconds = parseSeconds(ConfigManager.getDefault("bar-time", "10"));

            if (seconds == -1) seconds = 10;
        }

        BossBarHandler.sendBar(player, color, style, seconds, message.toString());
    }

    private static Player parsePlayer(String message) {
        Player player = Bukkit.getPlayer(message);

        if (player == null)
            return null;

        return player;
    }

    private static BarColor parseBarColor(String message) {
        try {
            return BarColor.valueOf(message.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    private static int parseSeconds(String message) {
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static BarStyle parseBarStyle(String message) {
        try {
            return BarStyle.valueOf(message.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
