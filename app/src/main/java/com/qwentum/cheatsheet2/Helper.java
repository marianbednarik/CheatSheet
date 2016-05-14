package com.qwentum.cheatsheet2;

import java.util.Calendar;

/**
 * Created by marian on 09/05/16.
 */
public class Helper {
    private static Calendar cal;

    public static int calendarGet (int i){
        cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        if (i == Calendar.DAY_OF_WEEK) {
            return cal.get(i) - 2;
        } else {
            return cal.get(i);
        }
    }
}
