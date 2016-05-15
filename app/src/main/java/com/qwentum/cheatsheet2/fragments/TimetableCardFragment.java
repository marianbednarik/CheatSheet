package com.qwentum.cheatsheet2.fragments;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwentum.cheatsheet2.Helper;
import com.qwentum.cheatsheet2.MainActivity;
import com.qwentum.cheatsheet2.R;
import com.qwentum.cheatsheet2.models.TimetableCardModel;
import com.qwentum.cheatsheet2.objects.Timetable;
import com.qwentum.cheatsheet2.objects.WeekDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Marian on 2016-02-29.
 */
public class TimetableCardFragment extends Fragment {
    private static final String TAG = "TTCardFragment";
    private final int TYPE_ITEM_0 = 0;
    private final int TYPE_ITEM_1 = 1;
    private final int TYPE_ITEM_2 = 2;

    private Timetable timetable;
    private SharedPreferences SP;
    private WeekDay currentDay;
    public List<TimetableCardModel> models;
    public RecyclerView recyclerView;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static TimetableCardFragment newInstance(int page) {
        //Log.d(TAG, "Generating Timetable with ID #" + (page) + "...");
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TimetableCardFragment fragment = new TimetableCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        timetable = new Timetable();
        SP = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        currentDay = timetable.getTimetable(getArguments().getInt(ARG_PAGE,0) + 2);

        models = new ArrayList<>();
        //Log.e(TAG, "Generating Cards with Day ID #" + (getArguments().getInt(ARG_PAGE,0) + 2) + "...");
        for (int i = 0; i < currentDay._classType.length; i++) {
            //Log.e(TAG, "Adding card #" + i + "...");
            models.add(i, new TimetableCardModel(i, currentDay));
            //Log.e(TAG, "Successfully added card #" + i);
        }
        //Log.e(TAG, "Done generating card");
        recyclerView = (RecyclerView) inflater.inflate(
                //recycler view xml
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public class TimetableCardViewHolder extends RecyclerView.ViewHolder {

        private TextView mSubjectNum, mSubjectName0, mSubjectName1, mSubjectName2, mSubjectInfo0, mSubjectInfo1, mSubjectInfo2, mSubjectID0, mSubjectID1, mSubjectID2, mTimes;
        private CardView mCardView;
        private ImageView mImageView0, mImageView1, mImageView2;

        public TimetableCardViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case TYPE_ITEM_2:
                    mImageView2 = (ImageView) itemView.findViewById(R.id.lessonColor_2);
                    mSubjectName2 = (TextView) itemView.findViewById(R.id.textCurrentSubject_2);
                    mSubjectInfo2 = (TextView) itemView.findViewById(R.id.textCurrentSubjectInfo_2);
                    mSubjectID2 = (TextView) itemView.findViewById(R.id.textCurrentSubjectID_2);
                case TYPE_ITEM_1:
                    mImageView1 = (ImageView) itemView.findViewById(R.id.lessonColor_1);
                    mSubjectName1 = (TextView) itemView.findViewById(R.id.textCurrentSubject_1);
                    mSubjectInfo1 = (TextView) itemView.findViewById(R.id.textCurrentSubjectInfo_1);
                    mSubjectID1 = (TextView) itemView.findViewById(R.id.textCurrentSubjectID_1);
                case TYPE_ITEM_0:
                    mImageView0 = (ImageView) itemView.findViewById(R.id.lessonColor_0);
                    mSubjectNum = (TextView) itemView.findViewById(R.id.textCurrentSubjectNum);
                    mSubjectName0 = (TextView) itemView.findViewById(R.id.textCurrentSubject_0);
                    mSubjectInfo0 = (TextView) itemView.findViewById(R.id.textCurrentSubjectInfo_0);
                    mSubjectID0 = (TextView) itemView.findViewById(R.id.textCurrentSubjectID_0);
                    mTimes = (TextView) itemView.findViewById(R.id.textTime);
                    mCardView = (CardView) itemView.findViewById(R.id.cardView);
                    break;
            }
        }

        public void bind(TimetableCardModel model, int viewType) {
            if (!model.mDisabled) {
                switch (viewType) {
                    case TYPE_ITEM_2:
                        mSubjectID2.setText(model.mSubjectID[2]);
                        mSubjectName2.setText(model.mSubjectName[2] + " - " + model.mGroup[2]);
                        mSubjectInfo2.setText(model.mSubjectInfo[2]);
                    case TYPE_ITEM_1:
                        mSubjectID1.setText(model.mSubjectID[1]);
                        mSubjectName1.setText(model.mSubjectName[1] + " - " + model.mGroup[1]);
                        mSubjectInfo1.setText(model.mSubjectInfo[1]);
                    case TYPE_ITEM_0:
                        mSubjectNum.setText(model.mSubjectNum);
                        mSubjectID0.setText(model.mSubjectID[0]);
                        if (model.mGroup[0] != "") {
                            mSubjectName0.setText(model.mSubjectName[0] + " - " + model.mGroup[0]);
                        } else {
                            mSubjectName0.setText(model.mSubjectName[0]);
                        }
                        mSubjectInfo0.setText(model.mSubjectInfo[0]);
                        mTimes.setText(model.mTimes);
                        updateColor(model, viewType);
                    break;
                }
            } else {
                mCardView.setVisibility(View.GONE);
            }
        }

        public void updateColor(TimetableCardModel model, int viewType) {
                switch (viewType) {
                    case TYPE_ITEM_2:
                        mImageView2.setColorFilter(ContextCompat.getColor(MainActivity.context, model.mSubjectIdColor[2]));
                    case TYPE_ITEM_1:
                        mImageView1.setColorFilter(ContextCompat.getColor(MainActivity.context, model.mSubjectIdColor[1]));
                    case TYPE_ITEM_0:
                        mImageView0.setColorFilter(ContextCompat.getColor(MainActivity.context, model.mSubjectIdColor[0]));
                        break;
                }
                if (model.mLessonDone && getArguments().getInt(ARG_PAGE,0) == Helper.calendarGet(Calendar.DAY_OF_WEEK)) {
                    //Log.d("Fragment","Settings colors with alpha");
                    switch (viewType) {
                        case TYPE_ITEM_2:
                            mImageView2.getDrawable().mutate().setAlpha(128);
                            mSubjectName2.setTypeface(Typeface.DEFAULT);
                        case TYPE_ITEM_1:
                            mImageView1.getDrawable().mutate().setAlpha(128);
                            mSubjectName1.setTypeface(Typeface.DEFAULT);
                        case TYPE_ITEM_0:
                            mImageView0.getDrawable().mutate().setAlpha(128);
                            mSubjectName0.setTypeface(Typeface.DEFAULT);
                            break;
                    }
                    mTimes.setText("Lesson has ended");
                    //mCardView.setAlpha(0.80f);
                }
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<TimetableCardViewHolder> {
        @Override
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
            if (!timetable.areLessonsDone(currentDay._classType.length)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                                .getLayoutManager();
                        layoutManager.scrollToPositionWithOffset(timetable.getCurrentSubjectID(true), 0);
                    }
                }, 10);
            }
        }

        public final List<TimetableCardModel> mModels;

        public ContentAdapter(List<TimetableCardModel> models) {
            mModels = models;
        }

        @Override
        public TimetableCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            switch (viewType) {
                case TYPE_ITEM_0:
                    itemView = LayoutInflater.from(MainActivity.context).
                        inflate(R.layout.item_timetable_card0, parent, false);
                    return new TimetableCardViewHolder(itemView, viewType);
                case TYPE_ITEM_1:
                    itemView = LayoutInflater.from(MainActivity.context).
                            inflate(R.layout.item_timetable_card1, parent, false);
                    return new TimetableCardViewHolder(itemView, viewType);
                case TYPE_ITEM_2:
                    itemView = LayoutInflater.from(MainActivity.context).
                            inflate(R.layout.item_timetable_card2, parent, false);
                    return new TimetableCardViewHolder(itemView, viewType);
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(TimetableCardViewHolder holder, int position) {
            TimetableCardModel model = mModels.get(position);
                holder.bind(model, holder.getItemViewType());
        }

        @Override
        public int getItemViewType(int position) {
            if (SP.getBoolean("personaliseTimetable", false)) {
                return TYPE_ITEM_0;
            }
            //Language exception
            if (currentDay._classType[position] == 3) {
                return TYPE_ITEM_1;
            }
            return currentDay._classType[position];
        }

        @Override
        public int getItemCount() {
            return mModels.size();
        }
    }
}
