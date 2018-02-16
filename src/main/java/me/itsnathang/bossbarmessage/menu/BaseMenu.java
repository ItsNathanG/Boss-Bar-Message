package me.itsnathang.bossbarmessage.menu;

import me.itsnathang.bossbarmessage.util.HeadIcon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.type.ChestMenu;

import java.util.Arrays;

public class BaseMenu {

    public static void sendMenu(Player player) {
        Menu menu = MainMenu.MainMenu();
        /*
        menu.getSlot(1)
                .setItem(HeadIcon.getHead("D"));

        menu.getSlot(2)
                .setItem(HeadIcon.getHead("E"));

        menu.getSlot(3)
                .setItem(HeadIcon.getHead("L"));

        menu.getSlot(4)
                .setItem(HeadIcon.getHead("E"));

        menu.getSlot(5)
                .setItem(HeadIcon.getHead("T"));

        menu.getSlot(6)
                .setItem(HeadIcon.getHead("E"));

        menu.getSlot(7)
                .setItem(HeadIcon.getHead("QUESTION"));
        */
        menu.open(player);
    }

    private static Menu createMenu() {
        return ChestMenu.builder(6)
                .title("Testing new menu stuff!")
                .build();
    }

    private static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList("Testing 1...", ChatColor.AQUA + "Testing 2..."));

        item.setItemMeta(meta);

        return item;
    }



}
