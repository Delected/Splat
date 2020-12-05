package me.delected.splat.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) return true;
        if (args[0].equalsIgnoreCase("c")) {
            Player p = (Player) sender;
            p.setGameMode(GameMode.CREATIVE);
            return true;
        } else if (args[0].equalsIgnoreCase("s")) {
            Player p = (Player) sender;
            p.setGameMode(GameMode.SURVIVAL);
            return true;
        } else {
            return true;
        }
    }
}
