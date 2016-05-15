package com.qwentum.cheatsheet2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

/**
 * Created by marian on 09/05/16.
 */
public class Helper {
    private static Calendar CAL;
    private static SharedPreferences SP;

    public static int calendarGet (int i){
        CAL = Calendar.getInstance();
        CAL.setFirstDayOfWeek(Calendar.MONDAY);
        if (i == Calendar.DAY_OF_WEEK) {
            return CAL.get(i) - 2;
        } else {
            return CAL.get(i);
        }
    }

    public static int getSharedPref(String pref) {
        SP = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        return Integer.parseInt(SP.getString(pref, "0"));
    }
}
