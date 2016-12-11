package org.itsmonkey.lms.utils;

import net.md_5.bungee.api.ChatColor;

/**
 * Created by Julio on 2/7/2016.
 */
public class StringUtils {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
