/*
 * Copyright 2019 lekro (kapurai).
 *
 * This file is part of frostywarp.
 *
 * frostywarp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * frostywarp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with frostywarp.  If not, see <https://www.gnu.org/licenses/>.
 */


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
