package org.itsmonkey.lms.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Created by Julio on 3/10/2016.
 */
public class LocationUtils {

    public static Location fromString(String section) {
        String[] split = section.split(",");
        World world = Bukkit.getWorld(split[0]);
        double x = Double.parseDouble(split[1]);
        double y = Double.parseDouble(split[2]);
        double z = Double.parseDouble(split[3]);
        float yaw = Float.parseFloat(split[4]);
        float pitch = Float.parseFloat(split[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String toString(Location location) {
        String separator = ",";
        return location.getWorld().getName() + separator + location.getX() +
                separator + location.getY() + separator + location.getZ() +
                separator + location.getYaw() + separator + location.getPitch()
                ;
    }

}
