package org.example.duck.k00kiesays.GameManager.MapManager;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SaveLoadMap implements CommandExecutor
{
    private final K00KieSays plugin;
    public SaveLoadMap(K00KieSays plugin)
    {
        this.plugin = plugin;
    }

    // TODO Move into PlayerCache
    private final Map<UUID, Tuple<Location, Location>> selections = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("You can't use this from console");
            return true;
        }
        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /schematics < pos1 | pos2 | save <name> | paste <name> >");
            return true;
        }
        Player p = (Player) sender;
        String param = args[0].toLowerCase();
        Tuple<Location, Location> selection = selections.getOrDefault(p.getUniqueId(), new Tuple<>(null, null));

        if ("pos1".equalsIgnoreCase(param))
        {
            selection.setFirst(p.getLocation());
            p.sendMessage(ChatColor.DARK_GREEN + "[K00Kie Says]" + ChatColor.GREEN + " First location is set");
            selections.put(p.getUniqueId(), selection);
        }else if ("pos2".equalsIgnoreCase(param))
        {
            selection.setSecond(p.getLocation());
            p.sendMessage(ChatColor.DARK_GREEN + "[K00Kie Says]" + ChatColor.GREEN + " Second location is set");
            selections.put(p.getUniqueId(), selection);
        }else if ("save".equalsIgnoreCase(param))
        {
            if (selection.getFirst() == null || selection.getSecond() == null)
            {
                p.sendMessage(ChatColor.RED + "Please select both position before saving the map");
                return true;
            }
            if (args.length != 2)
            {
                p.sendMessage(ChatColor.RED + "Usage: /schematics save <name>");
                return true;
            }
            File location = new File(plugin.getDataFolder(), "schematic/" + args[1] + ".schem");
            if (!location.getParentFile().exists()){
                location.getParentFile().mkdirs();
            }
                WorldEditHook.save(selection.getFirst(), selection.getSecond(), location);

            p.sendMessage(ChatColor.GREEN + "Schematics saved");
        }else if ("paste".equalsIgnoreCase(param)) {
            if (args.length != 2)
            {
                p.sendMessage(ChatColor.RED + "Usage: /schematics paste <name>");
                return true;
            }
            File file = new File(plugin.getDataFolder(), "schematic/" + args[1] + ".schem");

            if (!file.exists())
            {
                p.sendMessage(ChatColor.RED + "Schematic not found !");
                return true;
            }
            WorldEditHook.paste(p.getLocation(), file);
            p.sendMessage("Schematic pasted !");
        } else
        {
            p.sendMessage(ChatColor.RED + "Usage: /schematics < pos1 | pos2 | save <name> | paste <name> >");
        }

        return true;
    }

    private static class Tuple<A, B>{
        private A First;
        private B Second;

        public Tuple(A First, B Second)
        {
            this.First = First;
            this.Second = Second;
        }

        public A getFirst()
        { return First; }

        public void setFirst(A first) {
            this.First = first;
        }

        public B getSecond(){
        return Second;
        }

        public void setSecond(B second) {
            this.Second = second;
        }
    }
}
