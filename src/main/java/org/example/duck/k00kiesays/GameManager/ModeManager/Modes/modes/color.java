package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.MapManager.Map;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class color
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        Map.set("color");
        List<Material> blocks = List.of(Material.BLACK_CONCRETE, Material.RED_TERRACOTTA, Material.RED_CONCRETE
                , Material.YELLOW_CONCRETE, Material.YELLOW_TERRACOTTA, Material.GRAY_TERRACOTTA
                , Material.GRAY_CONCRETE);
        Material block = blocks.get(new Random().nextInt(blocks.size()));
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player i: players)
        {
            ModeAnnounce.Start("Stand on " + block.name(), i);
        }
        new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player i : players)
                {
                    Block block1 = Bukkit.getWorld(plugin.getConfig().getString("event-world")).getBlockAt
                    (i.getLocation().add(0, -1, 0));
                    if (block1.getType() == block)
                    {
                        plugin.getConfig().set("scores."+ i.getName(), plugin.getConfig().getInt("scores."+ i.getName()
                        + 3));
                        plugin.saveConfig();
                        new WinLose().win(i);
                    }
                }
                new ModeSwitcher().endMode();
            }
        }.runTaskLater(plugin, 100);
    }
}
