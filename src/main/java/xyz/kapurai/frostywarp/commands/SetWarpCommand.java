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
        
        StringBuilder sb = new StringBuilder();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                if (i > 1) sb.append(' ');
                sb.append(args[i]);
            }
        }
        String desc = sb.toString();


        if (warps.save(args[0], new Warp(p.getLocation(), desc))) {
            sender.sendMessage("Created warp "+args[0]+".");
        }

        return true;
    }

}
