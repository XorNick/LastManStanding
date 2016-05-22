package org.itsmonkey.lms.players;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.itsmonkey.lms.games.Game;

/**
 * Created by Julio on 2/1/2016.
 */
public class LMSPlayer {

    private Player player;
    private boolean joined;
    private int wins;
    private int kills;
    private int deaths;
    private Game game;

    public LMSPlayer(Player player){
        this.player = player;
    }

    public void sendMessage(String msg){
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
