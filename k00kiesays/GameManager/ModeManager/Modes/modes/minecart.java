package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.bukkit.entity.Entity;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class minecart
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        String world = plugin.getConfig().getString("event-world");
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            ModeAnnounce.Start("Get inside a minecart", p);
        }
        for (int i = 0; i < players.size() -1; i++)
        {
            Location spawn = plugin.getConfig().getLocation("spawn-location");
            spawn.setX(spawn.getX() + new Random().nextInt(-5, 6));
            spawn.setZ(spawn.getZ() + new Random().nextInt(-5, 6));
            Entity ch = Bukkit.getWorld(world).spawnEntity(spawn, EntityType.MINECART);
            ch.setCustomName("Animal");
            ch.setCustomNameVisible(false);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player i : players) {
                    if (i.isInsideVehicle()) {
                        plugin.getConfig().set("scores." + i.getName(), plugin.getConfig().getInt("scores." + i.getName()
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
