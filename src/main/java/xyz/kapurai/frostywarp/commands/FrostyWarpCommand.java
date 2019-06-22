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
