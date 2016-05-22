package org.itsmonkey.lms.utils;

/**
 * Created by Julio on 3/10/2016.
 */
public class IntUtils {

    public static boolean isInt(String input){
        try{
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
