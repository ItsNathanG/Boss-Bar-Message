package me.itsnathang.bossbarmessage.commands;

import static me.itsnathang.bossbarmessage.util.Translate.tl;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
    private final BossBarMessage plugin;

    public CommandHandler(BossBarMessage plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!checkPermission(sender, "bossbarmessage.admin"))
            return false;

        if (args.length == 0) {
            sendVersion(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            // /bm <send|broadcast|bc>
            case "send": case "broadcast": case "bc":
                if (args.length == 1) {
                    sender.sendMessage(tl("help_broadcast"));
                    return true;
                }

                new Message().sendBossBarMessage(plugin, sender, args);
                break;
            case "reload":
                reload(sender);
                break;
            case "version":
                sendVersion(sender);
                break;
            // /bm help or arg[0] not recognized
            case "help": default:
                sender.sendMessage(tl("help"));
        }

        return true;
    }

    private boolean checkPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission))
            return true;

        sender.sendMessage(tl("no_permission"));
        return false;
    }

    private void sendVersion(CommandSender sender) {
        sender.sendMessage(tl("version").replace("%version%", plugin.getDescription().getVersion()));
    }

    private void reload(CommandSender sender) {
        plugin.getConfigManager().reloadConfig();
        plugin.getLanguage().reloadLanguage();
        sender.sendMessage(tl("reload"));
    }
}
