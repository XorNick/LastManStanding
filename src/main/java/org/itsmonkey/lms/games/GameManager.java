package org.itsmonkey.lms.games;

import org.bukkit.configuration.file.FileConfiguration;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.players.LMSPlayer;
import org.itsmonkey.lms.utils.LocationUtils;

import java.util.*;

/**
 * Created by Julio on 3/10/2016.
 */
public class GameManager {

    private FileConfiguration config;

    public GameManager() {
        config = LMS.getInstance().getConfig();
    }

    private Map<String, Game> games = new HashMap<>();
    private List<Game> unfinishedGames = new ArrayList<>();

    /*
    Games:
      Wilderness:
        spawnLocation: world,x,y,z
        maxPlayers: 10
        ownWeapons: false
      Pyramid:
        spawnLocation: world,x,y,z
        maxPlayers: 5
        ownWeapons: true
     */

    public void loadGames() {
        for (String arena : config.getConfigurationSection("Games").getKeys(false)) {
            Game game = new Game(arena);
            game.setSpawnLocation(LocationUtils.fromString(config.getString("Games." + arena + ".spawnLocation")));
            game.setMaxPlayers(config.getInt("Games." + arena + ".maxPlayers"));
            game.setPlayers(new ArrayList<LMSPlayer>());
            game.setCommands(config.getStringList("Games." + arena + ".commands"));
            games.put(arena, game);
        }
    }

    public void createGame(String id) {
        Game game = new Game(id);
        games.put(id, game);
        unfinishedGames.add(game);
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public List<Game> getUnfinishedGames() {
        return unfinishedGames;
    }
}
