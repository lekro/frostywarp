package xyz.kapurai.frostywarp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.kapurai.frostywarp.Warps;

public final class WarpCommand extends FrostyWarpCommand {

    public WarpCommand(Warps warps) {
        super(warps);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (!validatePlayer(sender)) return true;
        Player p = (Player) sender;

        if (args.length == 0) {
            return false;
        }

        if (!warps.teleport(p, args[0])) {
            sender.sendMessage(ChatColor.RED + "Unknown warp.");
        }

        return true;
    }

}
