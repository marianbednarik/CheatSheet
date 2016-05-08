package com.qwentum.cheatsheet2.models;

import com.qwentum.cheatsheet2.objects.Timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by marian on 03/05/16.
 */
public class ClassmatesModel {

    public String classmateA, classmateB, dayRange, weekNum;

    public ClassmatesModel(int position) {
        //TODO Optimize this pile of feces
        SimpleDateFormat NameDateFormat = new SimpleDateFormat("dd MM yyyy");
        SimpleDateFormat classmatesDayDisplay = new SimpleDateFormat("d.MMM");
        Date startDate = null, endDate = null;
        Timetable timetable = new Timetable();
        int classmateNumber;


        String firstDate = "31 08 2015";
        String secondDate = NameDateFormat.format(new Date());

        try {
            startDate = NameDateFormat.parse(firstDate);
            endDate = NameDateFormat.parse(secondDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        classmateNumber = (int) TimeUnit.MILLISECONDS.toDays(endDate.getTime() - startDate.getTime()) / 7 - 1;

        classmateA = timetable.namesA[(classmateNumber + position) % 13];
        classmateB = timetable.namesB[(classmateNumber + position) % 14];

        long sum = startDate.getTime() + ((classmateNumber + 1 + position) * TimeUnit.DAYS.toMillis(7)) + TimeUnit.HOURS.toMillis(1);
        Date odDate = new Date(sum);
        sum += TimeUnit.DAYS.toMillis(6);
        Date doDate = new Date(sum);
        dayRange = classmatesDayDisplay.format(odDate) + " - " + classmatesDayDisplay.format(doDate);

        //TODO translation
        weekNum = (position + 1) + ". Week";
    }
}
