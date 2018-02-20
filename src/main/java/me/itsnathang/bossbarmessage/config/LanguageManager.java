package me.itsnathang.bossbarmessage.config;

import me.itsnathang.bossbarmessage.BossBarMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static me.itsnathang.bossbarmessage.util.Color.color;

public class LanguageManager {
    private static YamlConfiguration messages;
    private static BossBarMessage plugin;

    LanguageManager(BossBarMessage pl) {
        plugin = pl;

        messages = loadLanguage();
    }

    private static YamlConfiguration loadLanguage() {
        File language_file = new File(plugin.getDataFolder() + File.separator + "messages.yml");

        if (!language_file.exists())
            plugin.saveResource("messages.yml", false);

        return YamlConfiguration.loadConfiguration(language_file);
    }

    public static void reloadLanguage() { messages = loadLanguage(); }

    public static String getFilteredTranslation(String key) {
        return color(messages.getString(key)
                .replace("%prefix%", messages.getString("prefix"))
                .replace("%new_line%", "\n"));
    }

}
