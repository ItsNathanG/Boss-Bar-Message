package me.itsnathang.bossbarmessage.util;

import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

class BarCountdown extends BukkitRunnable {
    private final BossBar bar;
    private Integer timeLeft;
    private final Integer timeTotal;

    BarCountdown(BossBar b, int times) {
        this.bar = b;
        this.timeTotal = times;
        this.timeLeft = times;
    }

    @Override
    public void run() {
        if (timeLeft > 0) {
            bar.setProgress(timeLeft.doubleValue() / timeTotal.doubleValue());

            timeLeft--;
        } else {
            this.cancel();
        }
    }
}
