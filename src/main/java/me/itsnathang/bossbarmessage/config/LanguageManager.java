package me.itsnathang.bossbarmessage.config;

import me.itsnathang.bossbarmessage.BossBarMessage;
import me.itsnathang.bossbarmessage.util.Translate;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageManager {
    private YamlConfiguration messages;
    private BossBarMessage plugin;

    public LanguageManager(BossBarMessage plugin) {
        this.plugin = plugin;

        messages = loadLanguage();

        new Translate(messages);
    }

    private YamlConfiguration loadLanguage() {
        File language_file = new File(plugin.getDataFolder() + File.separator + "messages.yml");

        if (!language_file.exists())
            plugin.saveResource("messages.yml", false);

        return YamlConfiguration.loadConfiguration(language_file);
    }

    public void reloadLanguage() {
        messages = loadLanguage();

        new Translate(messages);
    }

}
