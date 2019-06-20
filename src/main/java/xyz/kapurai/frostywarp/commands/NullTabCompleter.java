package xyz.kapurai.frostywarp.commands;

import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class NullTabCompleter implements TabCompleter {

    private static final String[] emptyArray = {};
    private static final List<String> empty = Arrays.asList(emptyArray);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String alias, String[] args) {
        return empty;
    }

}
