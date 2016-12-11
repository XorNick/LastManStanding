package org.itsmonkey.lms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.utils.LocationUtils;
import org.itsmonkey.lms.utils.StringUtils;

/**
 * Created by Julio on 5/23/2016.
 */
public class InfoCommand extends CommandExecutor {

    public InfoCommand() {
        setCommand("info");
        setLength(2);
        setUsage("/lms info <game>");
        setBoth();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Game game = LMS.getInstance().getGameManager().getGame(args[1]);

        if (game == null) {
            sender.sendMessage(ChatColor.RED + "That game does not exist!");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder("&7&m-----------------------\n");
        stringBuilder.append("&eGame ID: &7").append(game.getId()).append("\n");
        stringBuilder.append("&eSpawn location: &7").append(LocationUtils.toString(game.getSpawnLocation())).append("\n");
        stringBuilder.append("&eMax Players: &7").append(game.getMaxPlayers()).append("\n");
        stringBuilder.append("&eOwn weapons: &7").append(game.isOwnWeapons()).append("\n");
        stringBuilder.append("&eJoinable: &7").append(game.isJoinable()).append("\n");
        stringBuilder.append("&eRunning: &7").append(game.isRunning()).append("\n");
        stringBuilder.append("&7&m-----------------------");

        sender.sendMessage(StringUtils.color(stringBuilder.toString()));
    }
}
