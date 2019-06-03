package xyz.kapurai.frostywarp;

import org.bukkit.Location;

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
        World world = Bukkit.getWorlds()[0];
        double x = map.getOrDefault("x", 0.0);
        double y = map.getOrDefault("y", 0.0);
        double z = map.getOrDefault("z", 0.0);
        double yaw = map.getOrDefault("yaw", 0.0);
        double pitch = map.getOrDefault("pitch", 0.0);
        String desc = map.getOrDefault("desc", null);

        if (map.containsKey("world")) {
            try {
                UUID uuid = UUID.fromString(map.get("world"));
                world = Bukkit.getWorld(uuid);
            } catch (IllegalArgumentException e) {}
        }

        return new Warp(new Location(world, x, y, z, yaw, pitch), desc);

    }

    public void teleport(Player p) {
        p.teleport(loc);
    }

}

