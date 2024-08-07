package org.example.duck.k00kiesays.GameManager.Commands.Locations;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinY implements CommandExecutor
{
    private final K00KieSays plugin;
    public MinY(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        Location loc = p.getLocation();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&Min Y is set"));
        plugin.getConfig().set("min-y", loc.getY());
        plugin.saveConfig();
        return true;
    }
}

