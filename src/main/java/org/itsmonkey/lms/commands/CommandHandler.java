package org.itsmonkey.lms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Julio on 2/7/2016.
 */
public class CommandHandler implements org.bukkit.command.CommandExecutor {

    private HashMap<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public CommandHandler(){
        commands.put("host", new HostCommand());
        commands.put("join", new JoinCommand());
        commands.put("creategame", new CreateGameCommand());
        commands.put("editgame", new EditGameCommand());
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(cmd.getName().equalsIgnoreCase("lms")){

            if(args.length == 0){
                sender.sendMessage("Some\nHelp\nList\nFor\nCommands");
                return true;
            }

            if(args[0] != null){
                String name = args[0].toLowerCase();
                if(commands.containsKey(name)){
                    final CommandExecutor command = commands.get(name);

                    if(command.getPermission() != null && !sender.hasPermission(command.getPermission())){
                        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                        return true;
                    }

                    if(!command.isBoth()){
                        if(command.isConsole() && sender instanceof Player){
                            sender.sendMessage(ChatColor.RED + "Only console can use that command!");
                            return true;
                        }
                        if(command.isPlayer() && sender instanceof ConsoleCommandSender){
                            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
                            return true;
                        }
                    }

                    if(command.getLength() > args.length){
                        sender.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
                        return true;
                    }

                    command.execute(sender, args);
                }
            }
        }

        return false;
    }
}

