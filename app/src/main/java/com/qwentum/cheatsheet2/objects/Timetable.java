package com.qwentum.cheatsheet2.objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qwentum.cheatsheet2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Marian on 2016-02-24.
 */
public class Timetable {
    private Calendar cal;

    public String[] times = {"07:05:00", "07:50:00", "07:55:00", "08:40:00", "08:50:00", "09:35:00",
            "09:45:00", "10:30:00", "10:40:00", "11:25:00", "11:30:00", "12:15:00",
            "12:45:00", "13:30:00", "13:35:00", "14:20:00", "14:25:00", "15:10:00"};
    public String[] timesReal = {"7:05", "7:50", "7:55", "8:40", "8:50", "9:35",
            "9:45", "10:30", "10:40", "11:25", "11:30", "12:15",
            "12:45", "13:30", "13:35", "14:20", "14:25", "15:10"};
    public String[] subjectNames = {"Anglický jazyk", "Elektronika",
            "Elektrotechnické meranie", "Informatika",
            "Matematika", "Nemecký jazyk",
            "Občianska náuka", "PC Architektúra",
            "Prax", "Programovanie",
            "Serverové technológie", "Sieťové technológie",
            "Slovenský jazyk", "Telesná výchova",
            "Ruský jazyk", "Prax", "Prax"};
    public String[] classroomNames = {"1. a 3.IT",
            "3.DA - 1C010", "3.AZ - 1D101",
            "3.AT - 1B001", "DRIM - 6B05",
            "1.AE - 1A011", "LCUP - 1F204",
            "LHAL - 6C108", "LHUD - 6A104",
            "LMIK - 1C109", "4.DP - 1A010",
            "TELM - 1TV2", "VYT - 1D107",
            "3.DZ - 1A009", "2.AE - 1D106",
            "LMOL - 6B106", "4.AA - 1C002",
            "S4 - 1C1032"};
    public String[] teacherNames = {"Kararína Košnarová", "Ondrej Rimovský",
            "Jozef Elek", "Juraj Szőcs",
            "Juliana Gyuríková", "Viera Kocholová",
            "Monika Barthová", "Peter Cucor",
            "Tibor Molnár", "Peter Hudec",
            "Roman Keszeg", "Michal Miko",
            "Lucia Kálaziová", "Anikó Decsi",
            "Katarína Antošíková", "Ondrej Rimovský",
            "Peter Cucor"};
    public String[] namesA = {"Adam Abrahám", "Marián Bednárik",
            "Richard Borbély", "Adrián Boros",
            "Roman Ďurovič", "Daniel Faludi",
            "Juraj Garaj", "Matej Holý",
            "Alexander Kaiser", "Eduard Krivánek",
            "Ján Kučera", "Marek Kurča", "Tomáš Žigo"};
    public String[] namesB = {"Michal Kuruc", "Gabriel Levai",
            "Patrik Máčik", "Tomáš Maťko",
            "Igor Mjasojedov", "Veronika Nagyová",
            "Patrik Németh", "Michal Packa",
            "Alex Sporni", "Dominik Telkesi",
            "Michal Toth", "Daniel Weis",
            "Matej Záhorský", "Marek Zeleňák"};
    public int[] subjectColors = {R.color.md_deep_orange_600, R.color.md_green_800,
            R.color.md_light_green_500, R.color.md_grey_600, R.color.md_pink_600, R.color.md_light_blue_500,
            R.color.md_deep_orange_800, R.color.md_cyan_700, R.color.md_teal_500, R.color.md_indigo_800,
            R.color.md_purple_800, R.color.md_yellow_700, R.color.md_orange_500, R.color.md_blue_700,
            R.color.md_red_500, R.color.md_teal_500, R.color.md_teal_500};
    public int[] subjectColorsOld = {R.color.md_deep_orange_200, R.color.md_green_200,
            R.color.md_light_green_200, R.color.md_grey_400, R.color.md_pink_200, R.color.md_light_blue_200,
            R.color.md_deep_orange_200, R.color.md_cyan_200, R.color.md_teal_200, R.color.md_indigo_200,
            R.color.md_purple_100, R.color.md_yellow_200, R.color.md_orange_200, R.color.md_blue_200,
            R.color.md_red_200, R.color.md_teal_200, R.color.md_teal_200};
    /*
    0.  Anglický jazyk	            1. a 3.IT - 1C107	Kararína Košnarová      Deep Orange 600     Normalna hodina
    1.  Elektronika	                3.DA - 1C010	    Ondrej Rimovský         Green 800           Dve skupiny
    2.  Elektrotechnické meranie	3.AZ - 1D101        Jozef Elek              Light Green 500     Try skupiny
    3.  Informatika	                3.AT - 1B001	    Juraj Szőcs             Grey 600
    4.  Matematika	                DRIM - 6B05	        Juliana Gyuríková       Pink 600
    5.  Nemecký jazyk 	            1.AE - 1A011	    Viera Kocholová         Light blue 500
    6.  Občianska náuka 	        LCUP - 1F204	    Monika Barthová         Deep orange 800
    7.  PC Architektúra	            LHAL - 6C108	    Peter Cucor             Cyan 700
    8.  Prax	                    LHUD - 6A104	    Tibor Molnár            Teal 500
    9.  Programovanie	            LMIK - 1C109	    Peter Hudec             Indigo 800
    10. Serverové technológie	    4.DP - 1A010	    Roman Keszeg            Purple 800
    11. Sieťové technológie	        TELM - 1TV2     	Michal Miko             Yellow 700
    12. Slovenský jazyk	            VYT - 1D107	        Lucia Kálaziová         Orange 500
    13. Telesná výchova		        3.DZ - 1A009        Anikó Decsi             Blue 700
    14. Ruský jazyk                 2.AE - 1D106        Katarína Antošíková     Red 500
    15. Prax                        LMOL - 6B106        Ondrej Rimovský         Teal 500
    16. Prax                        4.AA - 1C002        Peter Cucor             Teal 500
    17.                             S4 - 1C1032
    */

    private WeekDay monday = new WeekDay(false, new int[]{2, 2, 2, 3, 0, 0, 0, 0}, new int[][]{{16, 16, 16, 5, 1, 7, 12, 0}, {15, 15, 15, 14, 1, 7, 12, -1}, {8, 8, 8}}, new int[][]{{6, 6, 6, 13, 0, 14, 2, 0}, {4, 4, 4, 17, 0, 14, 2, 0}, {15, 15, 15}});
    private WeekDay tuesday = new WeekDay(false, new int[]{0, 0, 0, 0, 0, 0, 1, 0}, new int[][]{{7, 4, 13, 13, 1, 12, 3, 3}, {7, 4, 13, 13, 1, 12, 0, -1}}, new int[][]{{0, 0, 11, 11, 0, 0, 6, 6}, {0, 0, 11, 11, 0, 0, 16}});
    private WeekDay wednesday = new WeekDay(true, new int[]{0, 3, 2, 2, 2, 2, 2, 2}, new int[][]{{0, 5, 1, 1, 10, 10, 2, 2}, {-1, 14, 2, 2, 1, 1, 10, 10}, {0, 0, 10, 10, 2, 2, 1, 1}}, new int[][]{{0, 0, 4, 4, 12, 12, 7, 7}, {0, 17, 7, 7, 4, 4, 12, 12}, {0, 0, 12, 12, 7, 7, 4, 4}});
    private WeekDay thursday = new WeekDay(true, new int[]{0, 1, 0, 1, 1, 1, 1, 0}, new int[][]{{-1, 0, 4, 9, 9, 11, 11, -1}, {3, 3, 4, 11, 11, 9, 9, 0}}, new int[][]{{-1, 0, 0, 8, 8, 9, 9, -1}, {6, 6, 0, 9, 9, 8, 8, 0}});
    private WeekDay friday = new WeekDay(false, new int[]{0, 0, 0, 0, 0, 0}, new int[][]{{2, 6, 7, 12, 10, -1}, {2, 6, 7, 12, 10, 0}}, new int[][]{{3, 1, 10, 5, 0, -1}, {3, 1, 10, 5, 0, 0}});

    public WeekDay getTimetable(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return monday;
            case Calendar.TUESDAY:
                return tuesday;
            case Calendar.WEDNESDAY:
                return wednesday;
            case Calendar.THURSDAY:
                return thursday;
            case Calendar.FRIDAY:
                return friday;
            default:
                return null;
        }
    }

    public int getCurrentSubjectID(boolean real) {
        cal = Calendar.getInstance();
        Date currentTime = cal.getTime();
        int currentLesson;

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss/dd/MM/yyyy", Locale.GERMANY);

        String dateEnding = "/" + cal.get(Calendar.DAY_OF_MONTH) + '/' + (cal.get(Calendar.MONTH) + 1) + '/' + cal.get(Calendar.YEAR);
        try {
            if (currentTime.compareTo(timeFormat.parse(times[17] + dateEnding)) < 0) {
                for (int i = times.length - 1; i >= 0; i--) {
                    if (currentTime.compareTo(timeFormat.parse(times[i] + dateEnding)) >= 0) {
                        currentLesson = i;
                        if (!real) {
                            return currentLesson;
                        } else {
                            return currentLesson / 2;
                        }
                    }
                }
            } else {
                //Lessons over
                return times.length;
            }
        } catch (ParseException pe) {

        }
        //Couldn't get lesson number
        return -2;
    }

    public int getUserGroup(int typeOfSubject, Context context) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        int selectedName = Integer.parseInt(SP.getString("selectedName", "0"));
        if (SP.getBoolean("personaliseTimetable", false)) {
            switch (typeOfSubject) {
                case 1:
                case 0:
                    if (selectedName != 26) { //Zigo exception
                        if ((selectedName) > 11) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return 1;
                    }
                case 2:
                    if (selectedName < 9) {
                        return 0;
                    } else if (selectedName < 18 && selectedName > 8) {
                        return 1;
                    } else {
                        return 2;
                    }
                case 3:
                    switch (selectedName) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 7:
                        case 8:
                        case 9:
                        case 11:
                        case 13:
                        case 15:
                        case 18:
                        case 20:
                        case 21:
                        case 24:
                        case 25:
                            return 0;
                        case 5:
                        case 6:
                        case 10:
                        case 12:
                        case 14:
                        case 16:
                        case 17:
                        case 19:
                        case 22:
                        case 23:
                        case 26:
                            return 1;
                    }
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean areLessonsDone(int dayLength) {
            return getCurrentSubjectID(true) > dayLength;
    }

    public boolean isWeekend() {
        cal = Calendar.getInstance();
        return (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }
}
