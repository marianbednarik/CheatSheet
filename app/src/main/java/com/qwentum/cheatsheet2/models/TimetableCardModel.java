package com.qwentum.cheatsheet2.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qwentum.cheatsheet2.MainActivity;
import com.qwentum.cheatsheet2.objects.Timetable;
import com.qwentum.cheatsheet2.objects.WeekDay;

/**
 * Created by Marian on 2016-03-15.
 */
public class TimetableCardModel {

    public int[] mSubjectIdColor;
    public boolean mLessonDone = false, mDisabled = false;
    public String[] mSubjectName, mSubjectInfo, mSubjectID, mGroup;
    public String mTimes, mSubjectNum;

    public TimetableCardModel(int position, WeekDay currentDay) {
        //TODO id lesson is disabled skip everything

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        Timetable tt = new Timetable();
        int userGroup = tt.getUserGroup(currentDay._classType[position], MainActivity.context);
        int classType = currentDay._classType[position];
        mSubjectIdColor = new int[classType + 1];
        mSubjectID = new String[classType + 1];
        mGroup = new String[classType + 1];
        mSubjectName = new String[classType + 1];
        mSubjectInfo = new String[classType + 1];
        //TODO maybe only change the color when it's disabled (may not work)
        if (((!currentDay._startsWithZero && position*2 < tt.getCurrentSubjectID(false) - 2) || (currentDay._startsWithZero && position*2 < tt.getCurrentSubjectID(false))) && (!tt.areLessonsDone(currentDay._classType.length) && !tt.isWeekend())) {
            mLessonDone = true;
        }
        //Log.e("CardModel", "Creating Model #" + position);
        if (SP.getBoolean("personaliseTimetable", false)) { //Personalised
            int temp = currentDay._classNames[userGroup][position];
            if (temp != -1) {
                mDisabled = false;
                    mSubjectIdColor[0] = tt.subjectColors[temp];

                if (!currentDay._startsWithZero) {
                    mSubjectNum = Integer.toString(position + 1);
                } else {
                    mSubjectNum = Integer.toString(position);
                }
                mSubjectName[0] = tt.subjectNames[temp];
                mSubjectID[0] = mSubjectName[0].substring(0,1);
                mSubjectInfo[0] = tt.teacherNames[temp] + " - " + tt.classroomNames[currentDay._classInfo[userGroup][position]];
            } else {
                mDisabled = true;
            }
            mGroup[0] = "";
        } else { //All groups selected
            //Language Exception
            if (classType == 3) classType = 1;
            for (int i = 0; i <= classType; i++) {
                int temp = currentDay._classNames[i][position];
                if (temp == -1) {
                    temp = currentDay._classNames[i + 1][position];
                    mSubjectInfo[i] = tt.teacherNames[temp] + " - " + tt.classroomNames[currentDay._classInfo[i + 1][position]];
                } else {
                    mSubjectInfo[i] = tt.teacherNames[temp] + " - " + tt.classroomNames[currentDay._classInfo[i][position]];
                }
                    mSubjectIdColor[i] = tt.subjectColors[temp];

                if (!currentDay._startsWithZero) {
                    mSubjectNum = Integer.toString(position + 1);
                } else {
                    mSubjectNum = Integer.toString(position);
                }
                mSubjectName[i] = tt.subjectNames[temp];
                mSubjectID[i] = mSubjectName[i].substring(0,1);
            }
            switch (classType) {
                case 0:
                    if (currentDay._classNames[1][position] == -1 || currentDay._classNames[1].length <= position) {
                        mGroup[0] = "A";
                    } else if (currentDay._classNames[0][position] == -1) {
                        mGroup[0] = "B";
                    } else {
                        mGroup[0] = "";
                    }
                    break;
                case 1:
                    mGroup = new String[] {"A","B"};
                    break;
                case 2:
                    mGroup = new String[] {"S1","S2","S3"};
                    break;
            }
        }
        //For both personalised and all
        if (currentDay._startsWithZero) {
            mTimes = (tt.timesReal[position * 2] + " - " + tt.timesReal[position * 2 + 1]);
        } else {
            mTimes = (tt.timesReal[position * 2 + 2] + " - " + tt.timesReal[position * 2 + 3]);
        }
    }
}
