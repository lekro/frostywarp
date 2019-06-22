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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import xyz.kapurai.frostywarp.Warp;
import xyz.kapurai.frostywarp.Warps;

public class WarpTabCompleter implements TabCompleter {

    private static final String[] emptyArray = {};
    private static final List<String> empty = Arrays.asList(emptyArray);

    private Warps warps;

    public WarpTabCompleter(Warps warps) {
        this.warps = warps;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String alias, String[] args) {
        if (args.length <= 1) {
            return warps.getWarps().keySet().stream()
                .filter(k -> k.toLowerCase().startsWith(args[0].toLowerCase()))
                .sorted(Comparator.comparing(k -> k.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            return empty;
        }

    }

}
