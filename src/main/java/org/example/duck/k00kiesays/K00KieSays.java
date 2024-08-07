package org.example.duck.k00kiesays;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.duck.k00kiesays.GameManager.Commands.GameEnd;
import org.example.duck.k00kiesays.GameManager.Commands.GameStart;
import org.example.duck.k00kiesays.GameManager.Commands.Locations.*;
import org.example.duck.k00kiesays.GameManager.Commands.MoneyPerPoint;
import org.example.duck.k00kiesays.GameManager.Commands.ReloadConfig;
import org.example.duck.k00kiesays.GameManager.MapManager.SaveLoadMap;
import org.example.duck.k00kiesays.GameManager.ModeManager.Modes.Listener.Listener;

public final class K00KieSays extends JavaPlugin {

    private static K00KieSays plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("event-Start").setExecutor(new GameStart(this));
        this.getCommand("set-lose").setExecutor(new LosingLocation(this));
        this.getCommand("set-win").setExecutor(new WinningLocation(this));
        this.getCommand("set-start").setExecutor(new GameStartLocation(this));
        //this.getCommand("schematics").setExecutor(new SaveLoadMap(this));
        this.getCommand("reload").setExecutor(new ReloadConfig(this));
        this.getCommand("map").setExecutor(new OtherLocations(this));
        this.getCommand("reward").setExecutor(new MoneyPerPoint(this));
        this.getCommand("event-end").setExecutor(new GameEnd(this));
        this.getCommand("setminy").setExecutor(new MinY(this));
        getServer().getPluginManager().registerEvents(new Listener(this), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static K00KieSays getInstance()
    {
        return K00KieSays.getPlugin(K00KieSays.class);
    }
}
