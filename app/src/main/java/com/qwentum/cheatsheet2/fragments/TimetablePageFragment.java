package com.qwentum.cheatsheet2.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwentum.cheatsheet2.Helper;
import com.qwentum.cheatsheet2.R;
import com.qwentum.cheatsheet2.objects.Timetable;

import java.util.Calendar;

public class TimetablePageFragment extends Fragment {

    public TabLayout tabLayout;
    public ViewPager viewPager;
    private String TAG = "TTPageFragment";
    public SparseArray<TimetableCardFragment> generatedFragments = new SparseArray<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_timetable_page, container, false);

        tabLayout = (TabLayout) inflatedView.findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_tab_monday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_tab_tuesday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_tab_wednesday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_tab_thursday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_tab_friday));
        viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TimetablePagerAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        if (!Timetable.isWeekend()) {
            viewPager.setCurrentItem(Helper.calendarGet(Calendar.DAY_OF_WEEK));
        } else {
            viewPager.setCurrentItem(0);
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //TODO Models caching
                //generateModel();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return inflatedView;
    }

    public class TimetablePagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 5;

        public TimetablePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return TimetableCardFragment.newInstance(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TimetableCardFragment fragment = (TimetableCardFragment) super.instantiateItem(container, position);
            generatedFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            generatedFragments.remove(position);
        }
    }
}