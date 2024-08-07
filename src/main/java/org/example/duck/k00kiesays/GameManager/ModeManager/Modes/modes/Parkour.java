package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.MapManager.Map;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Parkour
{

    public void Enter()
    {
        Map.set("parkour");
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            ModeAnnounce.Start("Parkour to the top", p);
            p.getInventory().setHelmet(new ItemStack(Material.STONE_BUTTON, 1));
        }
        Timer.start(160);
    }
}
