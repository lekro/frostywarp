package xyz.kapurai.frostywarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import xyz.kapurai.frostywarp.Warps;

public final class DelWarpCommand extends FrostyWarpCommand {

    public DelWarpCommand(Warps warps) {
        super(warps);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (args.length == 0) {
            return false;
        }

        warps.getWarps().remove(args[0]);
        return true;
    }

}
