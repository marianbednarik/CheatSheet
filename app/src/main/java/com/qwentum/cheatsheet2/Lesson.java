package com.qwentum.cheatsheet2;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marian on 11/06/16.
 */
public class Lesson {
    private String mStart;
    private String mEnd;
    private String mTitle;
    private String mAcronym;
    private String mTeacher;
    private String mLocation;
    private int mIcon;
    private String mColor;

    public Lesson(JSONObject jsonObject) {
        try {
            mStart = jsonObject.getString("start");
            mEnd = jsonObject.getString("end");
            mTitle = jsonObject.getString("name");
            mAcronym = jsonObject.getString("acronym");
            mTeacher = jsonObject.getString("teacher");
            mLocation = jsonObject.getString("location");
            mIcon = jsonObject.getInt("icon");
            mColor = jsonObject.getString("color");
        } catch (JSONException ex) {
            Log.e("CS Lesson", ex.toString());
        }

    }

    public String getStart() {
        return mStart;
    }

    public String getEnd() {
        return mEnd;
    }

    public String getNameOf() {
        return mTitle;
    }

    public String getAcronym() {
        return mAcronym;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public String getLocation() {
        return mLocation;
    }

    public int getIcon() {
        return mIcon;
    }

    public String getColorOf() {
        return mColor;
    }
}
