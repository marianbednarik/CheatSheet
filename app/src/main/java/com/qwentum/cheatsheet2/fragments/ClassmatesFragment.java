package com.qwentum.cheatsheet2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qwentum.cheatsheet2.MainActivity;
import com.qwentum.cheatsheet2.R;
import com.qwentum.cheatsheet2.models.ClassmatesModel;

import java.util.ArrayList;
import java.util.List;


public class ClassmatesFragment extends Fragment {
    private static final String TAG = "TTCardFragment";

    public List<ClassmatesModel> models;
    public RecyclerView recyclerView;
    private int modelSize = 8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        models = new ArrayList<>();
        //Log.e(TAG, "Generating Cards with Day ID #" + (getArguments().getInt(ARG_PAGE,0) + 2) + "...");
        for (int i = 0; i < modelSize; i++) {
            //Log.e(TAG, "Adding card #" + i + "...");
            models.add(i, new ClassmatesModel(i));
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

    public class ClassmatesViewHolder extends RecyclerView.ViewHolder {

        TextView mClassmateA, mClassmateB, mDayRange, mWeekNum, mClassmateID_A, mClassmateID_B;

        public ClassmatesViewHolder(View itemView) {
            super(itemView);
            //Find views
            mClassmateA = (TextView) itemView.findViewById(R.id.classmateA);
            mClassmateB = (TextView) itemView.findViewById(R.id.classmateB);
            mDayRange = (TextView) itemView.findViewById(R.id.dayRange);
            mWeekNum = (TextView) itemView.findViewById(R.id.currentClassmateWeekNum);
            mClassmateID_A = (TextView) itemView.findViewById(R.id.classmateID_A);
            mClassmateID_B = (TextView) itemView.findViewById(R.id.classmateID_B);
        }

        public void bind(ClassmatesModel model) {
            //Set views
            mClassmateA.setText(model.classmateA);
            mClassmateB.setText(model.classmateB);
            mDayRange.setText(model.dayRange);
            mWeekNum.setText(model.weekNum);
            mClassmateID_A.setText(model.classmateA.substring(0,1));
            mClassmateID_B.setText(model.classmateB.substring(0,1));
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ClassmatesViewHolder> {
        // Set numbers of List in RecyclerView.
        @Override
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {

        }

        public final List<ClassmatesModel> mModels;

        public ContentAdapter(List<ClassmatesModel> models) {
            mModels = models;
        }

        @Override
        public ClassmatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(MainActivity.context).
                    inflate(R.layout.item_classmates, parent, false);
            return new ClassmatesViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(ClassmatesViewHolder holder, int position) {
            ClassmatesModel model = mModels.get(position);
            //if (!model.mDisabled) {
            //Log.e(TAG,"Binding card #" + position + "...");
            holder.bind(model);
            //}
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return mModels.size();
        }
    }
}
