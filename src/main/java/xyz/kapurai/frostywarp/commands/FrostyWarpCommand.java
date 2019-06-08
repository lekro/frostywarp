package xyz.kapurai.frostywarp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.kapurai.frostywarp.Warps;

public abstract class FrostyWarpCommand implements CommandExecutor {

    protected final Warps warps;

    public FrostyWarpCommand(Warps warps) {
        this.warps = warps;
    }

    public boolean validatePlayer(CommandSender sender) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED
                    + "you must be a player to execute this command");
            return false;
        }

        return true;

    }

}
