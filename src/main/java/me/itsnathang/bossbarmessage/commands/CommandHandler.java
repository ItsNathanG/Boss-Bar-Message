package me.itsnathang.bossbarmessage.commands;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.itsnathang.bossbarmessage.util.Translate.tl;

public class CommandHandler implements CommandExecutor {
    private BossBarMessage plugin;

    public CommandHandler(BossBarMessage plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("bossbarmessage.admin")) {
            sender.sendMessage(tl("no_permission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(tl("version").replace("%version%", plugin.getDescription().getVersion()));
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
                return true;
            // reload config files
            case "reload":
                plugin.getConfigManager().reloadConfig();
                plugin.getLanguage().reloadLanguage();
                sender.sendMessage(tl("reload"));
                return true;
            // send plugin version
            case "version":
                sender.sendMessage(tl("version").replace("%version%", plugin.getDescription().getVersion()));
                return true;
            // /bm help or arg[0] not recognized
            case "help": default:
                sender.sendMessage(tl("help"));
                return false;
        }
    }
}
