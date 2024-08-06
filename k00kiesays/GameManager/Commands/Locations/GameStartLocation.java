package org.example.duck.k00kiesays.GameManager.Commands.Locations;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameStartLocation implements CommandExecutor
{
    private final K00KieSays plugin;
    public GameStartLocation(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        Location loc = p.getLocation();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3[K00Kie Says]&a spawn is set to: X "
        + Double.toString(loc.getX()) + " Y " + Double.toString(loc.getY()) + " Z " +Double.toString(loc.getZ()) +
                " in world " + loc.getWorld().getName()));
        plugin.getConfig().set("spawn-location", loc);
        plugin.getConfig().set("event-world", loc.getWorld().getName());
        plugin.saveConfig();
        return true;
    }
}
