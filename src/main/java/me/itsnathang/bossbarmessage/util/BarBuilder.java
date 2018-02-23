package me.itsnathang.bossbarmessage.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class BarBuilder {
    private final BossBar bar;

    public BarBuilder(BarColor defaultColor, BarStyle defaultStyle) {
        this.bar = Bukkit.createBossBar("", defaultColor, defaultStyle);
    }

    public BarBuilder get() { return this; }

    public BarBuilder title(String title) {
        bar.setTitle(title);
        return this;
    }

    public BarBuilder color(BarColor color) {
        bar.setColor(color);
        return this;
    }

    public BarBuilder style(BarStyle style) {
        bar.setStyle(style);
        return this;
    }

    public BossBar build() {
        return bar;
    }

}
