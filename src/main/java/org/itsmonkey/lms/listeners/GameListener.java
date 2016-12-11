package org.itsmonkey.lms.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.itsmonkey.lms.events.PlayerLeaveGameEvent;
import org.itsmonkey.lms.games.Game;

/**
 * Created by Julio on 5/21/2016.
 */
public class GameListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerLeaveGameEvent event) {
        Player player = event.getPlayer().getPlayer();
        Game game = event.getGame();

        game.getPlayers().remove(event.getPlayer());
        game.sendMessage("&e" + event.getPlayer().getPlayer().getName() + " &ahas been eliminated");
        event.getPlayer().setGame(null);
        event.getPlayer().setJoined(false);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        if (game.getPlayers().size() == 1) {
            game.end();
        }

    }

}
