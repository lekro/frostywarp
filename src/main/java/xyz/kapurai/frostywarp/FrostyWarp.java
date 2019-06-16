package xyz.kapurai.frostywarp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import xyz.kapurai.frostywarp.commands.*;

public class FrostyWarp extends JavaPlugin {

    Warps warps;

    @Override
    public void onEnable() {

        // Prepare configuration to a usable state.
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        // Register our serializable class
        ConfigurationSerialization.registerClass(Warp.class, "warp");

        // Get warps
        warps = new Warps(getConfig().getConfigurationSection("warps"));

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
