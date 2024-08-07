package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Void
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        String world = plugin.getConfig().getString("event-world");
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players) {
            ModeAnnounce.Start("Jump in the void", p);
        }
        Timer.start(100);
    }
}
