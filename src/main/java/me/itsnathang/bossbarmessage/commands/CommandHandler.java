package me.itsnathang.bossbarmessage.commands;

import me.itsnathang.bossbarmessage.menu.BaseMenu;
import me.itsnathang.bossbarmessage.util.BossBarHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length < 1) {
            sender.sendMessage("Usage: /bossbarmessage [menu|bar]");
            return true;
        }

        switch (args[0]) {
            case "menu":
                BaseMenu.sendMenu((Player) sender);
                break;

            case "bar":
                BossBarHandler.sendBar("welcome", (Player) sender);
                break;

            case "help":
                sender.sendMessage("Coming soon...");
                break;
        }

        return true;
    }
}
