package org.itsmonkey.lms.games;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.players.LMSPlayer;
import org.itsmonkey.lms.utils.StringUtils;

import java.util.List;

/**
 * Created by Julio on 3/10/2016.
 */
public class Game {

    private List<LMSPlayer> players;
    private List<String> commands;
    private Location spawnLocation;
    private boolean running;
    private boolean joinable;
    private int maxPlayers;
    private String id;
    private int countdown;
    private boolean ownWeapons;

    public Game(String id) {
        this.id = id;
    }

    public void sendMessage(String msg) {
        for (LMSPlayer players : this.players) {
            players.sendMessage(msg);
        }
    }

    public void start() {
        this.joinable = false;
        sendMessage("&eThe game has started!");
        for (LMSPlayer player : this.players) {
            player.getPlayer().teleport(this.spawnLocation);
            player.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        }
    }

    public void end() {
        LMSPlayer winner = this.players.get(0);
        for (String command : this.commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", winner.getPlayer().getName()));
        }

        this.players.clear();
        this.joinable = false;
        this.running = false;

    }

    public void startCountdown() {
        this.countdown = 30;
        new BukkitRunnable() {
            public void run() {
                if (countdown == 0) {
                    start();
                    cancel();
                }

                if (countdown % 10 == 0 && countdown > 0) {
                    Bukkit.broadcastMessage(StringUtils.color("&e" + id + " &awill start in " + countdown + " second" + (countdown == 1 ? "" : "s")));
                }
                countdown--;
            }
        }.runTaskTimer(LMS.getInstance(), 0L, 20L);
    }

    public void addPlayer(LMSPlayer player) {
        this.players.add(player);
        player.setJoined(true);
        sendMessage("&e" + player.getPlayer().getName() + " &ahas joined the game!");
    }

    public void removePlayer(LMSPlayer player) {
        this.players.remove(player);
        player.setJoined(false);
        sendMessage("&e" + player.getPlayer().getName() + " &ahas left the game!");
    }

    public String getId() {
        return id;
    }

    public List<LMSPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LMSPlayer> players) {
        this.players = players;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public String getName() {
        return id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isOwnWeapons() {
        return ownWeapons;
    }

    public void setOwnWeapons(boolean ownWeapons) {
        this.ownWeapons = ownWeapons;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
