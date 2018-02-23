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
        // grab default values from config file
        BarColor color = parseBarColor(ConfigManager.getDefault("bar-color", "purple"));
        BarStyle style = parseBarStyle(ConfigManager.getDefault("bar-type", "solid"));
        int seconds = parseSeconds(ConfigManager.getDefault("bar-time", "10"));

        for (String arg : args) {
            String parsed;

            if ((parsed = readValue(arg, "player:", "p:")) != null) {

                if ((player = parsePlayer(parsed)) == null) {
                    sender.sendMessage(tl("player_not_online").replace("%value%", arg));
                    return;
                }

            } else if ((parsed = readValue(arg, "color:", "c:")) != null) {

                if ((color = parseBarColor(parsed)) == null) {
                    sender.sendMessage(tl("parse_color").replace("%value%", arg));
                    return;
                }

            } else if ((parsed = readValue(arg, "seconds:", "s:")) != null) {

                if ((seconds = parseSeconds(parsed)) == -1) {
                    sender.sendMessage(tl("parse_seconds").replace("%value%", arg));
                    return;
                }

            } else if ((parsed = readValue(arg, "permission:", "pe:")) != null) {

                // TODO: implement permission-based messages

            } else if ((parsed = readValue(arg, "type:", "t:")) != null) {

                if ((style = parseBarStyle(parsed)) == null) {
                    sender.sendMessage(tl("parse_type").replace("%value%", arg));
                    return;
                }

            } else { message.append(arg).append(" "); }
        }

        // Assign default values if couldn't read config & no input was provided.
        if (color == null) color = BarColor.PURPLE;

        if (style == null) style = BarStyle.SOLID;

        if (seconds == -1) seconds = 10;

        // Send bar to everyone on server if no player is specified.
        if (player == null) {
            BossBarHandler.sendGlobal(color, style, seconds, color(message.toString()));
            return;
        }

        // Send created bar to player specified.
        BossBarHandler.sendBar(player, color, style, seconds, color(message.toString()));
    }

    private static String readValue(String message, String... prefixes) {
        for (String prefix : prefixes)
            if (message.startsWith(prefix))
                return prefix.replace(prefix, "");

        return null;
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
