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
        getCommand("setwarp").setExecutor(new SetWarpCommand(warps));
        getCommand("delwarp").setExecutor(new DelWarpCommand(warps));
        getCommand("warps").setExecutor(new WarpsCommand(warps));

    }

    @Override
    public void onDisable() {

    }

}
