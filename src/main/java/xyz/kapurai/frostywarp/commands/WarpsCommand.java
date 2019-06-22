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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import xyz.kapurai.frostywarp.Warp;
import xyz.kapurai.frostywarp.Warps;
import xyz.kapurai.frostywarp.chat.ClickAction;
import xyz.kapurai.frostywarp.chat.Color;
import xyz.kapurai.frostywarp.chat.FancyMessage;
import xyz.kapurai.frostywarp.chat.HoverAction;

public class WarpsCommand extends FrostyWarpCommand {

    public WarpsCommand(Warps warps) {
        super(warps);
    }

    private FancyMessage formatWarp(Warp w) {
        String desc = w.getDescription();
        if (desc == null || desc.length() == 0) desc = "no description";

        FancyMessage outer = new FancyMessage(w.toString() + "\n");

        FancyMessage descMsg = new FancyMessage(desc + "\n");
        descMsg.color = Color.GRAY;
        descMsg.italic = true;
        FancyMessage clickMsg = new FancyMessage("(click to warp)");
        clickMsg.color = Color.GRAY;

        outer.add(descMsg);
        outer.add(clickMsg);

        return outer;
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
        int pages = (map.size() - 1) / 9 + 1;

        if (page < 0 || page >= pages) {
            String pageErrorMessage = String.format(
                    "Invalid page number. Only pages 1-%1$s exist.",
                    pages);
            sender.sendMessage(ChatColor.RED + pageErrorMessage);
            return true;
        }

        List<String> sortedKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);

        String heading = String.format("Warps (%1$d/%2$d) ", page+1, pages);
        FancyMessage outer = new FancyMessage(heading);
        FancyMessage m;

        // Prev/next buttons
        if (page > 0) {
            m = new FancyMessage("[<-]");
            m.color = Color.GRAY;
            m.hoverEvent = new HoverAction(HoverAction.Type.SHOW_TEXT,
                                          "go to page " + page);
            m.clickEvent = new ClickAction(ClickAction.Type.RUN_COMMAND,
                                          "/warps " + page);
            outer.add(m);
        }
        if (page < pages-1) {
            m = new FancyMessage("[->]");
            m.color = Color.GRAY;
            m.hoverEvent = new HoverAction(HoverAction.Type.SHOW_TEXT,
                                          "go to page " + (page+2));
            m.clickEvent = new ClickAction(ClickAction.Type.RUN_COMMAND,
                                          "/warps " + (page+2));
            outer.add(m);
        }

        for (int i = page*9; i < page*9+9 && i < sortedKeys.size(); i++) {
            String key = sortedKeys.get(i);
            Warp w = map.get(key);

            m = new FancyMessage('\n' + key);
            m.color = Color.GRAY;
            m.hoverEvent = new HoverAction(HoverAction.Type.SHOW_TEXT,
                                          formatWarp(w));
            m.clickEvent = new ClickAction(ClickAction.Type.RUN_COMMAND,
                                          "/warp " + key);
            outer.add(m);
        }


        outer.sendTo(p);
        return true;
    }
}
