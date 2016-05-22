package org.itsmonkey.lms.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.utils.StringUtils;

/**
 * Created by Julio on 2/7/2016.
 */
public class HostCommand extends CommandExecutor {

    public HostCommand(){
        setCommand("host");
        setPermission("lms.command.host");
        setUsage("/lms host <name>");
        setBoth();
        setLength(2);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String name = args[1];

        if(!LMS.getInstance().getGameManager().getGames().containsKey(args[1])){
            sender.sendMessage(StringUtils.color("&cThe map &4" + name + " &cdoes not exist!"));
            return;
        }

        Game game = LMS.getInstance().getGameManager().getGame(name);

        //TODO: Add a check to see if the game is finished or not

        if(game.isJoinable()){
            sender.sendMessage(StringUtils.color("&cThe game " + name + " is already joinable!"));
            return;
        }

        if(game.isRunning()){
            sender.sendMessage(StringUtils.color("&cThe game with name " + name + " is already running!"));
            return;
        }

        game.setJoinable(true);
        game.startCountdown();
        Bukkit.broadcastMessage(StringUtils.color("&eArena &6" + name + " &eis now joinable!\n&eType &6/lms join " + name + " &eto join!"));
    }
}
