package org.example.duck.k00kiesays.GameManager.MapManager;

import org.bukkit.entity.Player;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.File;
import java.util.List;

public class Map
{/*
    public static void resett()
    {
        Location loc = plugin.getConfig().getLocation("platform-location");
        final File file = new File(plugin.getDataFolder(), "schematic/defaultmap.schem");

        if (!file.exists())
        {
            System.out.println(ChatColor.RED + "Schematic not found !");
            return;
        }
        WorldEditHook.paste(loc, file);
    }*/

    public static void reset()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            Location spawn = plugin.getConfig().getLocation("spawn-location");
            p.teleport(spawn);
        }
    }

    public static void set(String map)
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            Location spawn = plugin.getConfig().getLocation(map);
            p.teleport(spawn);
        }
    }

    /*
    public static void sett(String map)
    {
        Location loc = plugin.getConfig().getLocation("platform-location");
        final File file = new File(plugin.getDataFolder(), "schematic/" + map.toLowerCase() + ".schem");

        if (!file.exists())
        {
            System.out.println(ChatColor.RED + map +" schematic not found !");
            return;
        }
        WorldEditHook.paste(loc, file);
    }*/
}
