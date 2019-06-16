package xyz.kapurai.frostywarp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import xyz.kapurai.frostywarp.ChatUtils;
import xyz.kapurai.frostywarp.Warps;
import xyz.kapurai.frostywarp.Warp;

public class WarpsCommand extends FrostyWarpCommand {

    public WarpsCommand(Warps warps) {
        super(warps);
    }

    private String formatWarp(Warp w) {
        StringBuilder sb = new StringBuilder();
        String desc = w.getDescription();
        if (desc == null || desc.length() == 0) desc = "no description";
        desc += "\\n";

        sb.append(ChatUtils.getJSONString(desc, "gray", false, true,
                    false, false, false, null, null, null, null, null));
        sb.append(',');
        sb.append(ChatUtils.getJSONString("(click to warp)", "gray", false,
                    false, false, false, false, null, null, null, null, null));

        return ChatUtils.getJSONString(w.toString()+"\\n", null, false, false,
                false, false, false, null, null, null, null, sb.toString());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        if (!validatePlayer(sender)) return true;
        Player p = (Player) sender;

        // We can fit 9 warps on one page of lines...
        int page = 0;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED
                        + "Page number must be a positive integer!");
                return true;
            }
        }

        Map<String, Warp> map = warps.getWarps();
        List<String> sortedKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);
        int pages = (map.size() - 1) / 9 + 1;

        StringBuilder sb = new StringBuilder();
        // Prev/next buttons
        boolean first = true;
        if (page > 0) {
            first = false;
            sb.append(ChatUtils.getJSONString("[<-]", "gray", false,
                    false, false, false, false, "show_text",
                    "\"go to page " + page+"\"", "run_command",
                    "/warps " + page, null));
        }
        if (page < pages-1) {
            if (!first) {
                sb.append(',');
            }
            sb.append(ChatUtils.getJSONString("[->]", "gray", false,
                    false, false, false, false, "show_text",
                    "\"go to page " + (page+2)+"\"", "run_command",
                    "/warps " + (page+2), null));
            first = false;
        }

        for (int i = page*9; i < page*9+9 && i < sortedKeys.size(); i++) {
            String key = sortedKeys.get(i);
            Warp w = map.get(key);
            if (!first) {
                sb.append(',');
            }
            first = false;
            sb.append(ChatUtils.getJSONString("\\n" + key, "gray", false,
                    false, false, false, false, "show_text",
                    formatWarp(w), "run_command",
                    "/warp " + key, null));
        }

        String finalJSON = ChatUtils.getJSONString(
                "Warps ("+(page+1)+"/"+pages+") ", null, false, false, false,
                false, false, null, null, null, null, sb.toString());

        ChatUtils.sendJSONMessage(p, finalJSON);

        return true;
    }
}
