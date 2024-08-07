package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;

import java.util.List;

public class Stop
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        List<Player> players = GetEventPlayers.getEventPlayers();
        List<String> list = List.of();
        for (Player p : players)
        {
            ModeAnnounce.Start("Stop", p);
            list.add(p.getName());
        }
        plugin.getConfig().set("won-players", list);
        plugin.saveConfig();
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                plugin.getConfig().set("current-mode", "stop2");
                plugin.saveConfig();
            }
        }.runTaskLater(plugin, 30);
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                List<String> list =plugin.getConfig().getStringList("won-players");
                for (Player p : players)
                {
                    if (list.contains(p.getName()))
                    {
                        plugin.getConfig().set("scores."+ p.getName(), plugin.getConfig().getInt("scores."+ p.getName()
                                + 3));
                        plugin.saveConfig();
                        new WinLose().win(p);
                    }
                }
            }
        }.runTaskLater(plugin, 99);
        Timer.start(1);
    }
}
