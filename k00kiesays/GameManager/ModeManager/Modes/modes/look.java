package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.Reward;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class look
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            ModeAnnounce.Start("Look at the sky", p);
        }
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                for (Player i : players)
                {
                    if (i.getLocation().getDirection().normalize().equals(new Vector(0,1,0)))
                    {
                        plugin.getConfig().set("scores."+ i.getName(), plugin.getConfig().getInt("scores."+ i.getName()
                                + 3));
                        plugin.saveConfig();
                        new WinLose().win(i);
                    }
                }
                new ModeSwitcher().endMode();
            }
        }.runTaskLater(plugin, 50);
    }
}
