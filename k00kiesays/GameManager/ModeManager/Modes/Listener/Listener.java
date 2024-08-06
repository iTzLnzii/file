package org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Listener;

import org.bukkit.Sound;
import org.bukkit.event.player.*;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.modes.Craft;
import org.example.duck.k00kiesays.GameManager.PlayerManager.GetEventPlayers;
import org.example.duck.k00kiesays.GameManager.PlayerManager.Reward;
import org.example.duck.k00kiesays.GameManager.PlayerManager.WinLose;
import org.example.duck.k00kiesays.K00KieSays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Listener implements org.bukkit.event.Listener
{
    private final K00KieSays plugin;
    public Listener(K00KieSays plugin)
    {
        this.plugin = plugin;
    }
    @EventHandler
    public void onFeed(PlayerInteractAtEntityEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("breed")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Player p = e.getPlayer();
            p.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
            e.setCancelled(true);
            p.hideEntity(plugin, e.getRightClicked());
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1 , 1);
            if (!p.getInventory().contains(Material.WHEAT_SEEDS))
            {
                Reward.give(p);
                e.getPlayer().teleport(plugin.getConfig().getLocation("winning-location"));
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,2);
            }
        }
    }

    @EventHandler
    public void onCakeEat(PlayerItemConsumeEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("cake")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (!(players.contains(e.getPlayer()))) return;
        if (e.getItem().getType() == Material.CAKE)
        {
            Reward.give(e.getPlayer());
            e.getPlayer().teleport(plugin.getConfig().getLocation("winning-location"));
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BURP, 1 , 1);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("craft")) return;
        if (e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();
            List<Player> players = GetEventPlayers.getEventPlayers();
            if (!players.contains(p)) return;
            if (e.getRecipe().getResult() == Craft.getResult())
            {
                    Reward.give(p);
                    p.teleport(plugin.getConfig().getLocation("winning-location"));
            }else{p.teleport(plugin.getConfig().getLocation("losing-location"));
            }
            p.getInventory().clear();
        }
    }

    @EventHandler
    public void Disk(PlayerInteractEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("disk")) return;
        if (e.getClickedBlock().getType() != Material.JUKEBOX) return;
        Player p = e.getPlayer();
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (!players.contains(p)) return;
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (p.getInventory().getItemInMainHand().getType() == Material.MUSIC_DISC_PIGSTEP)
        {
            Reward.give(p);
            p.getInventory().clear();
            e.setCancelled(true);
            p.teleport(plugin.getConfig().getLocation("winning-location"));
        }
    }

    @EventHandler
    public void eat(PlayerItemConsumeEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("eat")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            if (e.getItem().equals(new ItemStack(Material.COOKED_CHICKEN, 1)))
            {
                Player p = e.getPlayer();
                Reward.give(p);
                p.getInventory().clear();
                p.teleport(plugin.getConfig().getLocation("winning-location"));
            }else {
                e.getPlayer().getInventory().clear();
                e.getPlayer().teleport(plugin.getConfig().getLocation("losing-location"));
            }
        }
    }

    @EventHandler
    public void Parkour(PlayerMoveEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("parkour")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Reward.give(e.getPlayer());
            e.getPlayer().getInventory().clear();
            e.getPlayer().teleport(plugin.getConfig().getLocation("winning-location"));

        }
    }

    @EventHandler
    public void Shear(PlayerShearEntityEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("shear")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Reward.give(e.getPlayer());
            e.getPlayer().getInventory().clear();
            e.getPlayer().teleport(plugin.getConfig().getLocation("winning-location"));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Milk(PlayerBucketEntityEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("milk")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Reward.give(e.getPlayer());
            e.getPlayer().getInventory().clear();
            e.getPlayer().teleport(plugin.getConfig().getLocation("winning-location"));
        }
    }

    @EventHandler
    public void Shoot(ProjectileHitEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("shoot")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (!(e.getEntity().getShooter() instanceof Player)) return;
        if (players.contains((Player) e.getEntity().getShooter()))
        {
            Player i = (Player) e.getEntity().getShooter();
            try {
                if (e.getHitBlock().getType() == Material.SLIME_BLOCK) {
                    plugin.getConfig().set("scores." + i.getName(), plugin.getConfig().getInt("scores." + i.getName()
                            + 1));
                    plugin.saveConfig();
                    new WinLose().win(i);
                }
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void voidJump(PlayerMoveEvent e)
    {
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Player p = e.getPlayer();
            int minY = plugin.getConfig().getInt("min-y");
            if (p.getLocation().getY() < minY)
            {
                if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("void"))
                {
                    e.getPlayer().teleport(plugin.getConfig().getLocation("spawn-location"));
                    return;
                }
                p.teleport(plugin.getConfig().getLocation("winning-location"));
                Reward.give(p);
            }
        }
    }

    @EventHandler
    public void Stop(PlayerMoveEvent e)
    {
        if (!plugin.getConfig().getString("current-mode").equalsIgnoreCase("stop2")) return;
        List<Player> players = GetEventPlayers.getEventPlayers();
        if (players.contains(e.getPlayer()))
        {
            Player p = e.getPlayer();
            if (!plugin.getConfig().getStringList("won-players").contains(p.getName())) return;
            p.teleport(plugin.getConfig().getLocation("losing-location"));
            List<String> list = plugin.getConfig().getStringList("won-players");
            list.remove(p.getName());
            plugin.getConfig().set("won-players", list);
            plugin.saveConfig();
        }
    }
}
