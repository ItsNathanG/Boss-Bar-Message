package me.itsnathang.bossbarmessage.commands;

import static me.itsnathang.bossbarmessage.util.Color.color;
import static me.itsnathang.bossbarmessage.util.Translate.tl;

import me.itsnathang.bossbarmessage.BossBarMessage;

import me.itsnathang.bossbarmessage.util.BarBuilder;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class Message {

    public void sendBossBarMessage(BossBarMessage plugin, CommandSender sender, String[] args) {
        StringBuilder message = new StringBuilder();
        Player player = null;
        // set bar defaults
        BarBuilder bar = new BarBuilder(
                parseBarColor(plugin.getConfigManager().getDefault("bar-color", "purple")),
                parseBarStyle(plugin.getConfigManager().getDefault("bar-type", "solid"))).get();

        // grab default values from config file
        int seconds = parseSeconds(plugin.getConfigManager().getDefault("bar-time", "10"));

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            String parsed;

            if ((parsed = readValue(arg, "player:", "p:")) != null) {

                if ((player = parsePlayer(parsed)) == null) {
                    sender.sendMessage(tl("player_not_online").replace("%value%", parsed));
                    return;
                }

            } else if ((parsed = readValue(arg, "color:", "c:")) != null) {

                if (parseBarColor(parsed) == null) {
                    sender.sendMessage(tl("parse_color").replace("%value%", parsed));
                    return;
                }

                bar.color(parseBarColor(parsed));

            } else if ((parsed = readValue(arg, "seconds:", "s:")) != null) {

                if ((seconds = parseSeconds(parsed)) == -1) {
                    sender.sendMessage(tl("parse_seconds").replace("%value%", parsed));
                    return;
                }

            } /* else if ((parsed = readValue(arg, "permission:", "pe:")) != null) {

                // TODO: implement permission-based messages

            } */ else if ((parsed = readValue(arg, "type:", "t:")) != null) {

                if (parseBarStyle(parsed) == null) {
                    sender.sendMessage(tl("parse_type").replace("%value%", parsed));
                    return;
                }

                bar.style(parseBarStyle(parsed));

            } else { message.append(arg).append(" "); }
        }

        // Assign default values if couldn't read config & no input was provided.
        if (seconds == -1) seconds = 10;

        bar.title(color(message.toString()));

        // Send bar to everyone on server if no player is specified.
        if (player == null) {
            plugin.getBarHandler().sendGlobal(bar.build(), seconds);
            return;
        }

        // Send created bar to player specified.
        plugin.getBarHandler().sendBar(bar.build(), seconds, player);
    }

    private String readValue(String message, String... prefixes) {
        for (String prefix : prefixes)
            if (message.startsWith(prefix))
                return message.replace(prefix, "");

        return null;
    }

    private Player parsePlayer(String message) {
        Player player = Bukkit.getPlayer(message);

        if (player == null)
            return null;

        return player;
    }

    private BarColor parseBarColor(String message) {
        try {
            return BarColor.valueOf(message.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    private int parseSeconds(String message) {
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private BarStyle parseBarStyle(String message) {
        try {
            return BarStyle.valueOf(message.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
