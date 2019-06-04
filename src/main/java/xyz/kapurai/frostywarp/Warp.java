package xyz.kapurai.frostywarp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public class Warp implements Cloneable, ConfigurationSerializable {

    private Location loc;
    private String desc;

    public Warp(Location loc, String desc) {

        this.loc = loc.clone();
        this.desc = desc;

    }

    public Map<String, Object> serialize() {

        Map<String, Object> map = new HashMap<>();
        map.put("world", loc.getWorld().getUID().toString());
        map.put("x", new Double(loc.getX()));
        map.put("y", new Double(loc.getY()));
        map.put("z", new Double(loc.getZ()));
        map.put("yaw", new Float(loc.getYaw()));
        map.put("pitch", new Float(loc.getPitch()));
        if (desc != null) map.put("desc", desc);

        return map;

    }

    public static Warp deserialize(Map<String, Object> map) {

        // Specify default values in case of malformed object...
        World world = Bukkit.getWorlds().get(0);
        double x = (Double) map.getOrDefault("x", 0.0);
        double y = (Double) map.getOrDefault("y", 0.0);
        double z = (Double) map.getOrDefault("z", 0.0);
        float yaw = (Float) map.getOrDefault("yaw", 0.0);
        float pitch = (Float) map.getOrDefault("pitch", 0.0);
        String desc = (String) map.getOrDefault("desc", null);

        if (map.containsKey("world")) {
            try {
                UUID uuid = UUID.fromString((String) map.get("world"));
                world = Bukkit.getWorld(uuid);
            } catch (IllegalArgumentException e) {}
        }

        return new Warp(new Location(world, x, y, z, yaw, pitch), desc);

    }

    public void teleport(Player p) {
        p.teleport(loc);
    }

}

