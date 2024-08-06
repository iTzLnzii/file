package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;

import java.util.List;
import java.util.Random;

public class milk
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        String world = plugin.getConfig().getString("event-world");
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (int i = 0; i < 3; i++)
        {
            Location spawn = plugin.getConfig().getLocation("spawn-location");
            spawn.setX(spawn.getX() + new Random().nextInt(-5, 6));
            spawn.setZ(spawn.getZ() + new Random().nextInt(-5, 6));
            Entity ch = Bukkit.getWorld(world).spawnEntity(spawn, EntityType.COW);
            ch.setCustomName("Animal");
            ch.setCustomNameVisible(false);
        }
        for (Player p : players)
        {
            p.getInventory().addItem(new ItemStack(Material.BUCKET, 1));
            ModeAnnounce.Start("Milk the cow", p);
        }
        Timer.start(100);
    }
}
