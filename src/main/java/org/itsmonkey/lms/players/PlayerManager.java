package org.itsmonkey.lms.players;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Julio on 2/1/2016.
 */
public class PlayerManager {

    private HashMap<UUID, LMSPlayer> players = new HashMap<UUID, LMSPlayer>();

    public LMSPlayer getPlayer(Player player) {
        return players.get(player.getUniqueId());
    }

    public void load(Player player) {
        LMSPlayer lmsPlayer = new LMSPlayer(player);
        //TODO: Load stats
        players.put(player.getUniqueId(), lmsPlayer);
    }

    public void unload(Player player) {
        //TODO: save stats
        players.remove(player.getUniqueId());
    }

    public HashMap<UUID, LMSPlayer> getPlayers() {
        return players;
    }
}
