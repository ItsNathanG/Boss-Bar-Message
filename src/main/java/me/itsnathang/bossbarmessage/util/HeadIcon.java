package me.itsnathang.bossbarmessage.util;

import com.deanveloper.skullcreator.SkullCreator;
import com.google.common.base.Charsets;
import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.bukkit.Bukkit.getLogger;

public class HeadIcon {
    private static YamlConfiguration icons;

    public static void init(BossBarMessage plugin) {

        try {
            icons = YamlConfiguration.loadConfiguration((new BufferedReader(
                    new InputStreamReader(plugin.getResource("icons.yml"), Charsets.UTF_8))));
        } catch (Exception e) {
            getLogger().warning("Could not open icons.yml file!");
            icons = null;
        }

    }

    public static ItemStack getHead(String head) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skin = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

        skin.setDisplayName("");
        skull.setItemMeta(skin);

        return SkullCreator.withBase64(skull, icons.getString(head));
    }

    public static ItemStack getHead(String head, String item_name) {
        ItemStack skull = getHead(head);
        ItemMeta name = skull.getItemMeta();

        name.setDisplayName(ChatColor.translateAlternateColorCodes('&', item_name));
        skull.setItemMeta(name);

        return skull;
    }

}
