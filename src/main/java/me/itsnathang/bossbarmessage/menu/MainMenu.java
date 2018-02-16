package me.itsnathang.bossbarmessage.menu;

import me.itsnathang.bossbarmessage.util.HeadIcon;
import org.bukkit.ChatColor;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.type.ChestMenu;

public class MainMenu {

    public static Menu MainMenu() {
        Menu menu = ChestMenu.builder(6)
                .title("Boss Bar Message by NathanG")
                .build();

        // Global Announcements
        menu.getSlot(31).setItem(HeadIcon.getHead("GLOBE", "&rGlobal Announcement"));

        return menu;
    }

}
