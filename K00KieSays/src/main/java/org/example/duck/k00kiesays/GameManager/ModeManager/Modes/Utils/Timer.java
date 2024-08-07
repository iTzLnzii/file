package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer
{
    public static void start(int DurationInTicks)
    {
        K00KieSays plugin = K00KieSays.getInstance();
        new BukkitRunnable()
        {
            @Override
            public void run() {
                new ModeSwitcher().endMode();
            }
        }.runTaskLater(plugin, DurationInTicks);
    }
}
