package com.qwentum.cheatsheet2.objects;

/**
 * Created by marian on 08/05/16.
 */
public class WeekDay {
    public boolean _startsWithZero;
    public int[] _classType;
    public int[][] _classNames;
    public int[][] _classInfo;

    public WeekDay(boolean startsWithZero, int[] classType, int[][] classNames, int[][] classInfo) {
        _startsWithZero = startsWithZero;
        _classType = classType;
        _classNames = classNames;
        _classInfo = classInfo;
    }
}
