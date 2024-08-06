package org.example.duck.k00kiesays.GameManager.Commands.Locations;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.duck.k00kiesays.K00KieSays;

public class OtherLocations  implements CommandExecutor
{
    private final K00KieSays plugin;
    public OtherLocations(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        if (!(sender instanceof Player)) return false;
        if (args.length!= 2) return false;
        Player p = (Player) sender;
        Location loc = p.getLocation();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3[K00Kie Says]&a " + args[1] + " is set to your location"));
        plugin.getConfig().set(args[1], loc);
        plugin.saveConfig();
        return true;
    }
}

