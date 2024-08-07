package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class Cake
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        Location spawn = plugin.getConfig().getLocation("spawn-location");
        String world = spawn.getWorld().getName();
        Bukkit.getWorld(world).getBlockAt(spawn).setType(Material.CAKE);
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player i : players)
        {
            i.setFoodLevel(10);
            ModeAnnounce.Start("Eat from the cake", i);
        }
        Timer.start(100);
    }
}
