package me.itsnathang.bossbarmessage.util;

import org.bukkit.configuration.file.YamlConfiguration;

import static me.itsnathang.bossbarmessage.util.Color.color;

public class Translate {
    private static YamlConfiguration language;

    public Translate(YamlConfiguration language) {
        Translate.language = language;
    }

    public static String tl(String key) {
        return translate(key);
    }

    private static String translate(String key) {
        return getFilteredTranslation(key);
    }

    private static String getFilteredTranslation(String key) {
        return color(language.getString(key)
                .replace("%prefix%", language.getString("prefix"))
                .replace("%new_line%", "\n"));
    }
}
