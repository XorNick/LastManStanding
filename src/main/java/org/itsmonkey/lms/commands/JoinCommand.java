package org.itsmonkey.lms.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.players.LMSPlayer;

/**
 * Created by Julio on 3/29/2016.
 */
public class JoinCommand extends CommandExecutor {

    public JoinCommand(){
        setCommand("join");
        setLength(2);
        setUsage("/lms join <area>");
        setPlayer();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        LMSPlayer lmsPlayer = LMS.getInstance().getPlayerManager().getPlayer(player);

        Game game = LMS.getInstance().getGameManager().getGame(args[1]);
        if(game == null){
            lmsPlayer.sendMessage("&cThat is not a valid game");
            return;
        }

        if(!game.isJoinable()){
            lmsPlayer.sendMessage("&cThat game is not joinable");
            return;
        }

        if(game.isRunning()){
            lmsPlayer.sendMessage("&cThat game is already running");
            return;
        }

        game.addPlayer(lmsPlayer);
        lmsPlayer.setGame(game);
    }
}
