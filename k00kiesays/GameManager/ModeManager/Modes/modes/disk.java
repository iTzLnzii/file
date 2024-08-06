package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class disk
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        Location spawn = plugin.getConfig().getLocation("spawn-location");
        String world = spawn.getWorld().getName();
        Bukkit.getWorld(world).getBlockAt(spawn).setType(Material.JUKEBOX);
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player i : players)
        {
            i.getInventory().addItem(new ItemStack(Material.MUSIC_DISC_PIGSTEP, 1));
            ModeAnnounce.Start("Put the disk in the music disk", i);
        }
        Timer.start(60);
    }
}
