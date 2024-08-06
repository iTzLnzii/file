package org.example.duck.k00kiesays.GameManager.PlayerManager;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;

import java.util.List;

public class WinLose
{
    K00KieSays plugin = K00KieSays.getPlugin();

    public void win(Player p)
    {
        List<Player> won = (List<Player>) plugin.getConfig().get("won-players");
        won.add(p);
        plugin.getConfig().set("won-players", won);
        plugin.saveConfig();
    }
    public void lose(Player p)
    {
        List<Player> lose = (List<Player>) plugin.getConfig().get("lost-players");
        lose.add(p);
        plugin.getConfig().set("lost-players", lose);
        plugin.saveConfig();
    }
}
