package org.itsmonkey.lms.commands;

import org.bukkit.command.CommandSender;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.utils.StringUtils;

/**
 * Created by Julio on 5/3/2016.
 */
public class CreateGameCommand extends CommandExecutor {

    public CreateGameCommand() {
        setCommand("createarena");
        setPermission("lms.command.createarena");
        setLength(2);
        setBoth();
        setUsage("/lms createarena <name>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Game game = LMS.getInstance().getGameManager().getGame(args[1]);

        if (game != null) {
            sender.sendMessage(StringUtils.color("&cThe game &4" + game.getName() + " &calready exists!"));
            return;
        }

        LMS.getInstance().getGameManager().createGame(args[1]);
        sender.sendMessage(StringUtils.color("&aYou have created a game with ID &e" + args[1]));
    }
}
