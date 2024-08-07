package org.example.duck.k00kiesays.GameManager.PlayerManager;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;

import java.util.List;

public class WinLose
{
    K00KieSays plugin = K00KieSays.getInstance();

    public void win(Player p)
    {
        List<String> won = plugin.getConfig().getStringList("won-players");
        won.add(p.getName());
        plugin.getConfig().set("won-players", won);
        plugin.saveConfig();
    }
}
