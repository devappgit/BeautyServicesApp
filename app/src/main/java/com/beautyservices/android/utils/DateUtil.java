package com.beautyservices.android.utils;


import android.support.annotation.NonNull;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateUtil {

    // input formats
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // output formats
   private static final DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern("MMM dd");
   private static final DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("E MMM dd");

    public static String shortFormatOf(@NonNull String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, dateFormat);
        return dateTime.format(shortFormat);
    }

    public static String longFormatOf(@NonNull String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, dateFormat);
        return dateTime.format(longFormat);
    }
}
