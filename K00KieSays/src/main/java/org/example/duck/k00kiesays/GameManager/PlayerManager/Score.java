package org.example.duck.k00kiesays.GameManager.PlayerManager;

import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.entity.Player;

import java.util.List;

public class Score
{

    public void add(Player p)
    {
        K00KieSays plugin = K00KieSays.getInstance();
        List<String> won = (List<String>) plugin.getConfig().get("won-players");
        if (won.contains(p.getName())) return;
        plugin.getConfig().set("scores."+ p.getName(), plugin.getConfig().getInt("scores."+ p.getName()) +
                Math.max(3 - won.size(), 1));
        plugin.saveConfig();
    }
}
