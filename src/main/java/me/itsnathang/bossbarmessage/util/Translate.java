package me.itsnathang.bossbarmessage.util;

import me.itsnathang.bossbarmessage.config.LanguageManager;

public class Translate {

    public static String translate(String key) {
        return LanguageManager.getFilteredTranslation(key);
    }

    public static String tl(String key) {
        return translate(key);
    }

}
