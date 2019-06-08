package xyz.kapurai.frostywarp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

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

    }

    @Override
    public void onDisable() {

    }

}
