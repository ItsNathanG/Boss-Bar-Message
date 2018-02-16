package me.itsnathang.bossbarmessage.commands;

import me.itsnathang.bossbarmessage.menu.BaseMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        BaseMenu.sendMenu((Player) sender);

        return true;
    }
}
