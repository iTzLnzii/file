package org.example.duck.k00kiesays.GameManager.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.example.duck.k00kiesays.K00KieSays;

public class ReloadConfig implements CommandExecutor
{
    private final K00KieSays plugin;
    public ReloadConfig(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
    {
        plugin.saveConfig();
        plugin.reloadConfig();
        sender.sendMessage("Config reloaded");

        return true;
    }
}
