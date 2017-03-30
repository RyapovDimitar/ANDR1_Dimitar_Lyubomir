package com.example.andr1.fontysappliation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by az on 30.3.2017 г..
 */

public class Utils {

    public static final String FROM_SCHEDULE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FROM_GRADE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TO_DATE_FORMAT = "dd MMMM yyyy";
    public static final String TO_TIME_FORMAT = "HH:mm";

    public static String getDate(String origingDate, String fromFormat, String toFormat){

        final DateFormat fromDateFormat = new SimpleDateFormat(fromFormat);
        fromDateFormat.setLenient(false);

        DateFormat toDateFormat = new SimpleDateFormat(toFormat);
        toDateFormat.setLenient(false);

        try {
            Date date = fromDateFormat.parse(origingDate);
            return toDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
