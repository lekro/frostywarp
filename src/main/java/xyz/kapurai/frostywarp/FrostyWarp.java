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


package xyz.kapurai.frostywarp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import xyz.kapurai.frostywarp.commands.*;

public class FrostyWarp extends JavaPlugin {

    Warps warps;

    @Override
    public void onEnable() {

        // Register our serializable class
        ConfigurationSerialization.registerClass(Warp.class, "warp");

        // Prepare configuration to a usable state.
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        // Get warps
        warps = new Warps(this, "warps");

        // Register commands.
        getCommand("warp").setExecutor(new WarpCommand(warps));
        getCommand("warp").setTabCompleter(new WarpTabCompleter(warps));
        getCommand("setwarp").setExecutor(new SetWarpCommand(warps));
        getCommand("setwarp").setTabCompleter(new NullTabCompleter());
        getCommand("delwarp").setExecutor(new DelWarpCommand(warps));
        getCommand("delwarp").setTabCompleter(new WarpTabCompleter(warps));
        getCommand("warps").setExecutor(new WarpsCommand(warps));
        getCommand("warps").setTabCompleter(new NullTabCompleter());

    }

    @Override
    public void onDisable() {

    }

}
