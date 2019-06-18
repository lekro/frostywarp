package xyz.kapurai.frostywarp;

import java.util.Map;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public final class Warps {

    // Cached warps loaded from the config
    private Map<String, Warp> warps;
    private String confKey;
    private final FrostyWarp plugin;


    public Warps(FrostyWarp plugin, String confKey) {

        this.plugin = plugin;
        this.confKey = confKey;
        warps = new HashMap<>();
        ConfigurationSection conf = plugin.getConfig()
            .getConfigurationSection(confKey);
        load(conf, true);

    }


    /**
     * Load warps from the given configuration.
     *
     * @param conf - the configuration to read from
     * @param clobber - should we overwrite warps which are already cached?
     * @return the number of new or modified warps
     */
    public int load(ConfigurationSection conf, boolean clobber) {

        if (conf == null) return 0;
        int initialSize = warps.size();

        for (String key : conf.getKeys(false)) {
            // Avoid clobbering if we don't want to
            if (!clobber && warps.containsKey(key)) continue;
            warps.put(key, (Warp) conf.get(key));
        }

        return warps.size() - initialSize;

    }


    /**
     * Save the warp with the given key.
     *
     * @param conf - the configuration to save to
     * @param key - the warp name to save to
     * @return if the given warp actually existed
     */
    public boolean save(ConfigurationSection conf, String key) {

        Warp warp = warps.getOrDefault(key, null);
        conf.set(key, warp);
        plugin.saveConfig();

        return warp != null;

    }

    public boolean save(String key, Warp w) {
        ConfigurationSection conf = plugin.getConfig()
            .getConfigurationSection(confKey);
        if (w != null) warps.put(key, w);
        else warps.remove(key);
        return save(conf, key);
    }


    /**
     * Teleport a player to the warp with the given key.
     *
     * @return whether the key was found
     */
    public boolean teleport(Player p, String key) {

        Warp warp = warps.getOrDefault(key, null);
        if (warp != null) {
            warp.teleport(p);
        }

        return warp != null;

    }

    public Map<String, Warp> getWarps() {
        return warps;
    }

}
