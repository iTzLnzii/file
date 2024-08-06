package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ModeAnnounce
{
    public static void Start(String message, Player p)
    {
        p.sendTitle(" ", ChatColor.YELLOW + message, 1, 40, 20);
        p.sendMessage(ChatColor.GREEN + "K00Kie Says: " + ChatColor.YELLOW + "" + ChatColor.BOLD + message);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
    }
}
