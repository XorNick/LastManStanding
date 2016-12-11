package org.itsmonkey.lms.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.itsmonkey.lms.LMS;
import org.itsmonkey.lms.games.Game;
import org.itsmonkey.lms.utils.IntUtils;
import org.itsmonkey.lms.utils.StringUtils;

/**
 * Created by Julio on 5/3/2016.
 */
public class EditGameCommand extends CommandExecutor {

    public EditGameCommand() {
        setUsage("/lms editgame <game> <property> <value>");
        setCommand("editgame");
        setPlayer();
        setPermission("lms.command.editgame");
        setLength(3);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Game game = LMS.getInstance().getGameManager().getGame(args[1]);

        if (game == null) {
            sender.sendMessage(StringUtils.color("&cThat game does not exist\n&7You can create it using &e/lms creategame " + args[1]));
            return;
        }

        String property = args[2];

        if (property.equalsIgnoreCase("setLocation")) {
            game.setSpawnLocation(player.getLocation());
            player.sendMessage(StringUtils.color("&aYou have set &e" + args[1] + "'s &aspawn location!"));
        }

        else if (property.equalsIgnoreCase("setmaxplayers")) {

            if (!IntUtils.isInt(args[3])) {
                player.sendMessage(StringUtils.color("&4" + args[3] + " &cis not a number"));
                return;
            }

            game.setMaxPlayers(Integer.parseInt(args[3]));
            player.sendMessage(StringUtils.color("&aYou have set &e" + args[1] + "'s &amax players to &e" + args[3]));
        }

        else if (property.equalsIgnoreCase("ownWeapons")) {

            if (!args[3].equalsIgnoreCase("true") && !args[3].equalsIgnoreCase("false")) {
                player.sendMessage(StringUtils.color("&cUsage: /lms editgame " + args[1] + " ownWeapons true|false"));
                return;
            }

            game.setOwnWeapons(Boolean.valueOf(args[3]));
            player.sendMessage(StringUtils.color("&aYou have set own-weapons for &e" + args[1] + " &ato &e" + args[3]));
        }

        else {
            player.sendMessage(StringUtils.color("&cThat is an invalid property"));
        }
    }
}
