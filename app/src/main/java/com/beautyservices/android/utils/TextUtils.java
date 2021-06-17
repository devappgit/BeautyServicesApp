package com.beautyservices.android.utils;


import android.support.annotation.NonNull;

public class TextUtils {

    public static String parsePhpSessionId(@NonNull String phpSessionString) {
        // PHPSESSID=b8ff81ce1140539c6d33e0b1c8c4f0e4; expires=Sat, 04-Nov-2017 19:30:46 GMT; Max-Age=3600;

        String subString = phpSessionString.substring(0, phpSessionString.indexOf(";"));    // parse out PHPSESSID=b8ff81ce1140539c6d33e0b1c8c4f0e4
        return subString.substring((subString.indexOf("=") + 1));  // parse out b8ff81ce1140539c6d33e0b1c8c4f0e4
    }

    public static boolean isEmpty(String str) {
        return (str != null && str.isEmpty());
    }
}
