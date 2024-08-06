package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes;

import org.example.duck.k00kiesays.GameManager.MapManager.Map;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.ModeAnnounce;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils.Timer;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Shoot
{
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getPlugin();
        Map.set("shoot");
        Location spawn = plugin.getConfig().getLocation("spawn-location");
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            ItemStack bow = new ItemStack(Material.BOW, 1);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.INFINITY,1,true);
            bow.setItemMeta(bowMeta);
            p.getInventory().addItem(bow);
            p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
            ModeAnnounce.Start("Shoot the slimes", p);
        }
        Timer.start(200);
    }
}
