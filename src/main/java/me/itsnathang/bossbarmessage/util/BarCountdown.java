package me.itsnathang.bossbarmessage.util;

import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

public class BarCountdown extends BukkitRunnable {
    private final BossBar bar;
    private Integer time_left;
    private Integer total_time;

    BarCountdown(BossBar b, int times) {
        this.bar = b;
        this.total_time = times;
        this.time_left = times;
    }

    @Override
    public void run() {
        if (time_left > 0) {
            bar.setProgress(time_left.doubleValue() / total_time.doubleValue());

            time_left--;
        } else {
            this.cancel();
        }
    }
}
