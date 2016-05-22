package org.itsmonkey.lms.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.players.LMSPlayer;

/**
 * Created by Julio on 5/21/2016.
 */
public class PlayerLeaveGameEvent extends Event {

    public static HandlerList handlerList = new HandlerList();

    private LMSPlayer player;
    private Game game;

    public PlayerLeaveGameEvent(LMSPlayer player, Game game) {
        this.player = player;
        this.game = game;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public LMSPlayer getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }
}
