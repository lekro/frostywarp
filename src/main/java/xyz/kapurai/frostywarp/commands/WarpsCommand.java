package xyz.kapurai.frostywarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.kapurai.frostywarp.Warps;
import xyz.kapurai.frostywarp.Warp;

public class WarpsCommand implements CommandExecutor {

    private final Warps warps;

    public WarpsCommand(Warps warps) {
        this.warps = warps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player to execute this command");
            return 1;
        }

        // We can fit 9 warps on one page of lines...
        int page = 0;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("page number must be a positive integer!");
                return 1;
            }
        }

        Map<String, Warp> map = warps.getWarps();
        List<String> sortedKeys = new ArrayList<>(map.getKeys());
        Collections.sort(sortedKeys);
        int pages = (map.size() - 1) / 9 + 1;

        StringBuilder sb = new StringBuilder();
        // Prev/next buttons
        if (page > 0) {
            sb.append(ChatUtils.getJSONString("[prev]", "gray", false,
                    false, false, false, false, null, null, "run_command",
                    "/warps " + page, null));
        }
        if (page < pages) {
            sb.append(ChatUtils.getJSONString("[next]", null, false,
                    false, false, false, false, null, null, "run_command",
                    "/warps " + (page+2), null));
        }

        sb.append(ChatUtils.getJSONString("\n", null, false,
                false, false, false, false, null, null, null, null);

        for (int i = page*9; i < page*9+9 && i < sortedKeys.size(); i++) {
            String key = sortedKeys.get(i);
            sb.append(ChatUtils.getJSONString(key + '\n', null, false,
                    false, false, false, false, null, null, "run_command",
                    "/warp " + key, null));
        }

        String finalJSON = ChatUtils.getJSONString(
                "Warps ("+(page+1)+"/"+pages+") ", null, false, false, false
                false, false, null, null, null, null, sb.toString());

        ChatUtils.sendJSONMessage(p, finalJSON);

        return true;
    }
}
