package xyz.kapurai.frostywarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.kapurai.frostywarp.Warps;
import xyz.kapurai.frostywarp.Warp;

public final class SetWarpCommand extends FrostyWarpCommand {

    public SetWarpCommand(Warps warps) {
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

        warps.getWarps().put(args[0], new Warp(p.getLocation(), ""));

        return true;
    }

}
