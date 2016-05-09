package com.qwentum.cheatsheet2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by marian on 09/05/16.
 */
public class Helper {
    private static Calendar cal;
    //private static SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);

    public static int calendarGet (int i){
        cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //Log.d("HelperClass","Current day is " + cal.get(i));
        //Log.d("HelperClass", "Sunday = " + Calendar.SATURDAY);
        //if (SP.getBoolean("mondayIsFirstDay", true)){
        if (i == Calendar.DAY_OF_WEEK) {
            return cal.get(i) - 2;
        } else {
            return cal.get(i);
        }
    }
}
