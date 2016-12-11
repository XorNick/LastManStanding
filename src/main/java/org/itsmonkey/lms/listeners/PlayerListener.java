package org.itsmonkey.lms.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.events.PlayerLeaveGameEvent;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.players.LMSPlayer;

/**
 * Created by Julio on 2/1/2016.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LMS.getInstance().getPlayerManager().load(player);

        LMSPlayer lmsPlayer = LMS.getInstance().getPlayerManager().getPlayer(player);
        lmsPlayer.sendMessage("&aWelcome to the server!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        LMSPlayer lmsPlayer = LMS.getInstance().getPlayerManager().getPlayer(event.getPlayer());
        Game game = lmsPlayer.getGame();

        if (game == null) return;

        Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(lmsPlayer, game));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        LMSPlayer lmsPlayer = LMS.getInstance().getPlayerManager().getPlayer(player);
        Game game = lmsPlayer.getGame();

        if (game == null) return;

        event.getDrops().clear();
        Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(lmsPlayer, game));

        if (game.getPlayers().size() == 1) {
            game.end();
        }

    }

}
