package org.example.duck.k00kiesays.GameManager.Commands;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeSwitcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;

public class GameStart implements CommandExecutor
{
    private final K00KieSays plugin;
    public GameStart(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (!p.getWorld().getName().equalsIgnoreCase(plugin.getConfig().getString("event-world")))
        {
            p.sendMessage(ChatColor.RED + "You have to be in the event world to start the event, use /seteventworld");
            return true;
        }
        if (plugin.getConfig().getLocation("spawn-location") == null)
        {
            p.sendMessage(ChatColor.RED + "Event spawn location is not set, use /seteventspawn");
            return true;
        }
        for (Player i : Bukkit.getOnlinePlayers())
        {
            if (i.getWorld().getName().equalsIgnoreCase(plugin.getConfig().getString("event-world"))
                    &&  i.getGameMode() == GameMode.SURVIVAL)
            {
                plugin.getConfig().set("alive-players", plugin.getConfig().getInt("alive-players") +1);
                String title = plugin.getConfig().getString("game-start-title-message");
                String subtitle = plugin.getConfig().getString("game-start-subtitle-message");
                assert title != null;
                assert subtitle != null;
                i.sendTitle(ChatColor.translateAlternateColorCodes('&', title)
                        , ChatColor.translateAlternateColorCodes('&', subtitle), 1 ,40 ,1);
                i.playSound(i.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1 , 1);
                plugin.getConfig().set("scores." + i.getName(), 0);
                GetEventPlayers.setEventPlayers(plugin.getConfig().getString("event-world"));
            }
            plugin.saveConfig();
        }
        ModeSwitcher modeSwitcher = new ModeSwitcher();
        modeSwitcher.startEvent();
        return true;
    }
}
