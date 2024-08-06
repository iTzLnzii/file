package org.example.duck.k00kiesays.GameManager.PlayerManager;

import org.bukkit.Sound;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;

import java.util.List;

public class Reward
{
    public static void give(Player p)
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        String world = plugin.getConfig().getString("event-world");
        List<Player> players = GetEventPlayers.getEventPlayers();
        if(players.contains(p))
        {
            new Score().add(p);
            new WinLose().win(p);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 , 2);
            if (plugin.getConfig().getStringList("won-players").size() == players.size())
            {
                new ModeSwitcher().endMode();
            }
        }
    }
}
