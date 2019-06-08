package xyz.kapurai.frostywarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import xyz.kapurai.frostywarp.Warp;
import xyz.kapurai.frostywarp.Warps;

public final class DescWarpCommand extends FrostyWarpCommand {

    public DescWarpCommand(Warps warps) {
        super(warps);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (args.length < 2) {
            return false;
        }

        if (!warps.getWarps().containsKey(args[0])) {
            sender.sendMessage(ChatColor.RED + "Unknown warp.");
            return true;
        }

        Warp w = warps.getWarps().get(args[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) sb.append(' ');
            sb.append(args[i]);
        }
        w.setDescription(sb.toString());

        return true;
    }

}
