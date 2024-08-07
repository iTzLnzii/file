package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class Eat
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            List<ItemStack> food = List.of(new ItemStack(Material.SPIDER_EYE, 1),
                    new ItemStack(Material.TROPICAL_FISH, 1),
                    new ItemStack(Material.CHICKEN, 1),
                    new ItemStack(Material.ROTTEN_FLESH, 1),
                    new ItemStack(Material.COOKED_CHICKEN, 1));
            for (ItemStack item : food) {;
                p.getInventory().addItem(item);
            }
            ModeAnnounce.Start("Eat healthy", p);
            p.setFoodLevel(10);
        }
        Timer.start(120);
    }
}
