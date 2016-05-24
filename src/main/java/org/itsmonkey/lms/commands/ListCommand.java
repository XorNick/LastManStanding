package org.itsmonkey.lms.commands;

import org.bukkit.command.CommandSender;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.utils.StringUtils;

/**
 * Created by Julio on 5/23/2016.
 */
public class ListCommand extends CommandExecutor {

    public ListCommand() {
        setCommand("list");
        setUsage("/lms list");
        setLength(1);
        setBoth();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("&7&m---------------------\n");
        for(Game game : LMS.getInstance().getGameManager().getGames().values()){
            stringBuilder.append("&e- ").append("&7").append(game.getId()).append("\n");
        }
        stringBuilder.append("&7&m---------------------");

        sender.sendMessage(StringUtils.color(stringBuilder.toString()));
    }
}
