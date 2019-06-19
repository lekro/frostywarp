package xyz.kapurai.frostywarp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs("warp")
public class Warp implements Cloneable, ConfigurationSerializable {

    private String desc;

    // Store coordinates and world uuid in location
    private double x, y, z;
    private float pitch, yaw;
    private UUID worldId;

    public Warp(Location loc, String desc) {

        this(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw(),
             loc.getWorld().getUID(), desc);

    }

    public Warp(double x, double y, double z, float pitch, float yaw,
                UUID worldId, String desc) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.worldId = worldId;
        this.desc = desc;

    }

    public String getDescription() {
        return desc;
    }

    public Map<String, Object> serialize() {

        Map<String, Object> map = new HashMap<>();
        map.put("world", worldId.toString());
        map.put("x", new Double(x));
        map.put("y", new Double(y));
        map.put("z", new Double(z));
        map.put("yaw", new Float(yaw));
        map.put("pitch", new Float(pitch));
        if (desc != null) map.put("desc", desc);

        return map;

    }

    public static Warp deserialize(Map<String, Object> map) {

        // Specify default values in case of malformed object...
        UUID worldId = Bukkit.getWorlds().get(0).getUID();
        double x = (Double) map.getOrDefault("x", 0.0);
        double y = (Double) map.getOrDefault("y", 0.0);
        double z = (Double) map.getOrDefault("z", 0.0);
        float yaw = ((Double) map.getOrDefault("yaw", 0.0)).floatValue();
        float pitch = ((Double) map.getOrDefault("pitch", 0.0)).floatValue();
        String desc = (String) map.getOrDefault("desc", null);

        if (map.containsKey("world")) {
            try {
                worldId = UUID.fromString((String) map.get("world"));
            } catch (IllegalArgumentException e) {}
        }

        return new Warp(x, y, z, yaw, pitch, worldId, desc);

    }

    public World getWorld() {
        // Try to find the world with the given UID...
        World world = Bukkit.getWorld(worldId);
        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }
        return world;
    }

    public boolean inWorld(World world) {
        return worldId == world.getUID();
    }

    /**
     * Teleport a player to this warp.
     *
     * @param p - the player to teleport
     * @return location of actual teleport, or null if no teleport happened.
     */
    public Location teleport(Player p) {
        Location loc = new Location(getWorld(), x, y, z, yaw, pitch);
        if (!p.teleport(loc)) return null;
        return loc;
    }
    
    public String toString() {
        String format = "(X=%1$d, Y=%2$d, Z=%3$d) in %4$s";
        World world = getWorld();
        String worldName = (inWorld(world)) ? world.getName() : "[unknown]";
        return String.format(format, x, y, z, worldName);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Warp)) return false;
        if (obj == this) return true;
        Warp warp = (Warp) obj;
        return warp.x == x && warp.y == y && warp.z == z
            && warp.yaw == yaw && warp.pitch == pitch
            && warp.desc.equals(desc);
    }

    public boolean equalsLocation(Location loc) {
        return equals(new Warp(loc, desc));
    }

}

