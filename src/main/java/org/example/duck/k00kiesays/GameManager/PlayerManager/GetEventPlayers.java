package org.example.duck.k00kiesays.GameManager.PlayerManager;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GetEventPlayers
{
    private static List<Player> eventPlayers = List.of();
    public static void setEventPlayers(String world)
    {
        List<Player> players = new ArrayList<>();
        for(Player i : Bukkit.getOnlinePlayers())
        {
            if (i.getWorld().getName().equalsIgnoreCase(world))
            {
                if (i.getGameMode() == GameMode.SURVIVAL)
                {
                    players.add(i);
                }
            }
        }
        eventPlayers = players;
    }

    public static List<Player> getEventPlayers()
    {
        for (Player i : eventPlayers)
        {
            if (!i.isOnline())
            {
                eventPlayers.remove(i);
            }
        }
        return eventPlayers;
    }

    public static void clearEventPlayer()
    {
        eventPlayers = List.of();
    }
}
