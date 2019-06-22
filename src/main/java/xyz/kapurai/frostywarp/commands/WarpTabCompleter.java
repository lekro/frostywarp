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
