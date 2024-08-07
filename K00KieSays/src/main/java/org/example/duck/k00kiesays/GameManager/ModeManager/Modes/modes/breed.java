package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class breed
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        String world = plugin.getConfig().getString("event-world");
        for (Player p : GetEventPlayers.getEventPlayers())
        {
            Entity ch = Bukkit.getWorld(world).spawnEntity(p.getLocation(), EntityType.CHICKEN);
            ch.setCustomName("Animal");
            ch.setCustomNameVisible(false);
        }
        for (Player p : GetEventPlayers.getEventPlayers())
        {
            p.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, 64));
            ModeAnnounce.Start("Feed the chickens", p);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0, false, false));
        }
        Timer.start(80);
    }
}
