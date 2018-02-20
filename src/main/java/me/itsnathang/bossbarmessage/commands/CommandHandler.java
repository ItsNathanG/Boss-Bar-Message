package me.itsnathang.bossbarmessage.commands;

import me.itsnathang.bossbarmessage.BossBarMessage;
import me.itsnathang.bossbarmessage.config.ConfigManager;
import me.itsnathang.bossbarmessage.config.LanguageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.itsnathang.bossbarmessage.util.Translate.tl;

public class CommandHandler implements CommandExecutor {
    private static BossBarMessage plugin;

    public CommandHandler(BossBarMessage plugin) {
        CommandHandler.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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

                Message.sendBossBarMessage(sender, args);
                return true;
            case "reload":
                ConfigManager.reloadConfig();
                LanguageManager.reloadLanguage();
                sender.sendMessage(tl("reload"));
                return true;

            // /bm help or arg[0] not recognized
            default: case "help":
                sender.sendMessage(tl("help"));
                return true;
        }
    }
}
