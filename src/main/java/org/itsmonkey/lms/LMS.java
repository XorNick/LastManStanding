package org.itsmonkey.lms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.itsmonkey.lms.commands.CommandHandler;
import org.itsmonkey.lms.games.GameManager;
import org.itsmonkey.lms.listeners.GameListener;
import org.itsmonkey.lms.listeners.PlayerListener;
import org.itsmonkey.lms.players.PlayerManager;

/**
 * Created by Julio on 2/1/2016.
 */
public class LMS extends JavaPlugin {

    private static LMS instance;
    private PlayerManager playerManager;
    private GameManager gameManager;

    /**
     * Episode 7: Fix up code, list command, info command
     */

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.playerManager = new PlayerManager();
        this.gameManager = new GameManager();
        this.gameManager.loadGames();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new GameListener(), this);

        getCommand("lms").setExecutor(new CommandHandler());

    }

    public static LMS getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
