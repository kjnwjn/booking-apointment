package com.quanpham.demo.utils;

import java.util.regex.Pattern;

public class UtilsHelper {
    public static String formatSqlAsSimpleDate(java.sql.Date sqlDate, String format) {
        String defaultFormat = "dd/MM/yyyy";
        if (format != null && format.length() > 0) {
            defaultFormat = format;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(defaultFormat);
        return sdf.format(sqlDate.getTime());
    }

    public static Boolean CheckUUID(String id) {

        Pattern UUID_REGEX = Pattern
                .compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        if (!UUID_REGEX.matcher(id).matches()) {
            return false;
        }
        return true;
    }

    public static boolean emailMatch(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
