package org.itsmonkey.lms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.itsmonkey.lms.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julio on 2/7/2016.
 */
public class CommandHandler implements org.bukkit.command.CommandExecutor {

    private Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public CommandHandler() {
        commands.put("host", new HostCommand());
        commands.put("join", new JoinCommand());
        commands.put("creategame", new CreateGameCommand());
        commands.put("editgame", new EditGameCommand());
        commands.put("info", new InfoCommand());
        commands.put("list", new ListCommand());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lms")) {

            if (args.length == 0) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&7&m--------------------\n");
                for (CommandExecutor command : commands.values()) {

                    if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                        stringBuilder.append("");
                    } else {
                        stringBuilder.append("&e- &7").append(command.getCommand()).append("\n");
                    }

                }
                stringBuilder.append("&7&m--------------------");
                sender.sendMessage(StringUtils.color(stringBuilder.toString()));

                return true;
            }

            if (args[0] != null) {
                String name = args[0].toLowerCase();
                if (commands.containsKey(name)) {
                    final CommandExecutor command = commands.get(name);

                    if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                        return true;
                    }

                    if (!command.isBoth()) {
                        if (command.isConsole() && sender instanceof Player) {
                            sender.sendMessage(ChatColor.RED + "Only console can use that command!");
                            return true;
                        }
                        if (command.isPlayer() && sender instanceof ConsoleCommandSender) {
                            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
                            return true;
                        }
                    }

                    if (command.getLength() > args.length) {
                        sender.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
                        return true;
                    }

                    command.execute(sender, args);
                }
            }
        }

        return false;
    }

    public Map<String, CommandExecutor> getCommands() {
        return commands;
    }

}

