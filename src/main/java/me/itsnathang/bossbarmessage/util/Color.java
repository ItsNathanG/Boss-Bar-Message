package me.itsnathang.bossbarmessage.util;

import java.util.regex.Pattern;

public class Color {
    // CREDIT: LuckyPerms (MIT License)
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");

    // CREDIT: LuckyPerms (MIT License)
    public static String color(String s) {
        char[] b = s.toCharArray();

        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    // CREDIT: LuckyPerms (MIT License)
    public static String stripColor(String s) {
        return s == null ? null : STRIP_COLOR_PATTERN.matcher(s).replaceAll("");
    }

}