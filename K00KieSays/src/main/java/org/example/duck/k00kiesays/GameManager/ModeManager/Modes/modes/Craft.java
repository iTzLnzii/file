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
import java.util.Random;

public class Craft
{
    public static ItemStack result;
    public void Enter()
    {
        K00KieSays plugin = K00KieSays.getInstance();
        Map.set("craft");
        List<ItemStack> materials = List.of(new ItemStack(Material.STRING, 64),
                new ItemStack(Material.OAK_PLANKS, 64), new ItemStack(Material.COBBLESTONE, 64),
                new ItemStack(Material.QUARTZ, 64),
                new ItemStack(Material.WHEAT, 64),
                new ItemStack(Material.OBSIDIAN, 64),
                new ItemStack(Material.BLAZE_POWDER, 64),
                new ItemStack(Material.STICK, 64),
                new ItemStack(Material.MOSS_BLOCK, 64),
                new ItemStack(Material.AMETHYST_SHARD, 64),
                new ItemStack(Material.IRON_INGOT, 64),
                new ItemStack(Material.GLASS, 64),
                new ItemStack(Material.DIAMOND, 64),
                new ItemStack(Material.REDSTONE, 64));
        List<ItemStack> results = List.of(new ItemStack(Material.FURNACE, 1),
                new ItemStack(Material.MOSSY_COBBLESTONE, 1),
                new ItemStack(Material.LADDER, 1),
                new ItemStack(Material.CHEST, 1),
                new ItemStack(Material.BUCKET, 1),
                new ItemStack(Material.COMPASS, 1),
                new ItemStack(Material.NOTE_BLOCK, 1),
                new ItemStack(Material.JUKEBOX, 1));
        result = results.get(new Random().nextInt(results.size() -1 ));
        List<Player> players = GetEventPlayers.getEventPlayers();
        for (Player p : players)
        {
            for (ItemStack i : materials)
            {
                p.getInventory().addItem(i);
            }
            ModeAnnounce.Start("Craft a " + result.getType().name(), p);
        }
        Timer.start(160);
    }
    public static ItemStack getResult()
    {
        return result;
    }
}
