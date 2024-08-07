package org.example.duck.k00kiesays.GameManager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;

public class GameEnd implements CommandExecutor
{
    private final K00KieSays plugin;
    public GameEnd(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        GetEventPlayers.clearEventPlayer();
        plugin.getConfig().set("current-mode", "none");
        Bukkit.getScheduler().cancelTasks(plugin);
        plugin.saveConfig();

        return true;
    }
}
