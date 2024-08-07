package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils;

import org.bukkit.*;
import org.example.duck.k00kiesays.GameManager.MapManager.Map;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes.*;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes.Void;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ModeSwitcher
{

    static List<String> Modes;
    public void startEvent()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        new BukkitRunnable()
        {
            @Override
            public void run() {
                Modes = plugin.getConfig().getStringList("all-modes");
                if (!plugin.getConfig().getBoolean("play-all-games"))
                {
                    List<String> newModes = new ArrayList<>();
                    for ( int i = 0 ; i == plugin.getConfig().getInt("modes-to-play"); i++)
                    {
                        String Mode = Modes.get((int) new Random().nextInt(Modes.size() -1));
                        Modes.remove(Mode);
                        newModes.add(Mode);
                    }
                    Modes = newModes;
                }
                if (plugin.getConfig().getBoolean("allow-games-to-repeat"))
                {
                    for (String i : Modes)
                    {
                        if (Math.random() > 0.75)
                        {
                            Modes.add(i);
                        }
                    }
                }
                plugin.getConfig().set("mode-list", Modes);
                plugin.saveConfig();
                changeMode();
            }
        }.runTaskLater(plugin, 140);

    }

    public void endMode()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        if (plugin.getConfig().getString("current-mode").equalsIgnoreCase("none")) return;
        Location spawn = plugin.getConfig().getLocation("spawn-location");
        String world = spawn.getWorld().getName();
        Bukkit.getWorld(world).getBlockAt(spawn).setType(Material.AIR);
        plugin.getConfig().set("current-mode", "none");
        plugin.saveConfig();
        List<String> winners = plugin.getConfig().getStringList("won-players");
        assert world != null;
        Map.reset();
        for (Entity i : Objects.requireNonNull(Bukkit.getWorld(world)).getEntities())
        {
            if (i instanceof Player)
            {
                Player p = (Player) i;
                if (!GetEventPlayers.getEventPlayers().contains(p)) return;
                p.getInventory().clear();
                p.setHealth(20);
                p.setFoodLevel(20);
                if (winners.contains(p.getName()))
                {
                    p.sendTitle(ChatColor.GREEN + "Good job, you succeed", "", 1, 20,1);
                    p.playSound(i.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 ,2);
                }
                else{
                    p.sendTitle(ChatColor.RED + "You failed", "", 1, 20,1);
                    p.playSound(i.getLocation(), Sound.ENTITY_VILLAGER_NO, 1 ,1);
                }
                continue;
            }
            if (i.getName().equalsIgnoreCase("Animal")) i.remove();
        }
        new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player p : GetEventPlayers.getEventPlayers())
                {
                    p.sendTitle(ChatColor.GREEN + "K00Kie Says", "", 1, 20,30);
                }
            }
        }.runTaskLater(plugin, 60);
        new BukkitRunnable()
        {
            @Override
            public void run() {
                changeMode();
            }
        }.runTaskLater(plugin, 80);
    }

    public void changeMode()
    {
        K00KieSays plugin = K00KieSays.getInstance();

        for (Player i : GetEventPlayers.getEventPlayers())
        {
            i.playSound(i.getLocation(), Sound.ENTITY_VILLAGER_YES, 1 , 1);
        }
        String Mode;

        if (Modes.isEmpty()) {
            for (Player i : GetEventPlayers.getEventPlayers())
            {
                i.getInventory().clear();
                i.sendTitle(ChatColor.GREEN + "Good Game!", "wish you had fun :)", 20, 60,30);
                i.playSound(i.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 , 1);
                int money = plugin.getConfig().getInt("scores." + i.getName());
                int moneyperpoint = plugin.getConfig().getInt("money-per-point");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give" + i.getName() + moneyperpoint*money);
            }
            return;
        }

        Mode = Modes.get(new Random().nextInt(Modes.size()));
        Modes.remove(Mode);
        plugin.getConfig().set("mode-list", Modes);
        plugin.getConfig().set("current-mode", "none");
        plugin.getConfig().set("won-players", List.of(""));
        switch (Mode)
        {
            case "craft":
                new Craft().Enter();
                break;
            case "stop":
                new Stop().Enter();
                break;
            case "milk":
                new milk().Enter();
                break;
            case "parkour":
                new Parkour().Enter();
                break;
            case "sneak":
                new Sneak().Enter();
                break;
            case "void":
                new Void().Enter();
                break;
            case "cake":
                new Cake().Enter();
                break;
            case "shoot":
                new Shoot().Enter();
                break;
            case "eat":
                new Eat().Enter();
                break;
            case "shear":
                new shear().Enter();
                break;
            case "breed":
                new breed().Enter();
                break;
            case "color":
                new color().Enter();
                break;
            case "minecart":
                new minecart().Enter();
                break;
            case "disk":
                new disk().Enter();
                break;
            case "look":
                new look().Enter();
                break;
        }
        plugin.getConfig().set("current-mode", Mode);
        plugin.saveConfig();
    }
}
