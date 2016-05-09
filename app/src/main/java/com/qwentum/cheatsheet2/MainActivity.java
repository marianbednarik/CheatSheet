package com.qwentum.cheatsheet2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.qwentum.cheatsheet2.fragments.ClassmatesFragment;
import com.qwentum.cheatsheet2.fragments.TimetableCardFragment;
import com.qwentum.cheatsheet2.fragments.TimetablePageFragment;
import com.qwentum.cheatsheet2.objects.Timetable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Context context;
    TimetablePageFragment timetablePageFragment;
    TextView textTimer;
    Toolbar toolbar;
    Timetable timetable = new Timetable();
    Calendar cal;
    FragmentTransaction fragmentTransaction;
    SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Normal Content view
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            timetablePageFragment = new TimetablePageFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, timetablePageFragment).commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting the title
        textTimer = new TextView(this);
        setTitle(R.string.main_activity_name);
        context = getBaseContext();

        //TIMER
        if (timetable.getCurrentSubjectID(false) < 18) {
            try {
                //Log.e("Main", "Current subject ID is " + timetable.getCurrentSubjectID(false));
                cal = Calendar.getInstance();
                startTimer(tf.parse(timetable.times[timetable.getCurrentSubjectID(false) + 1]).getTime() - tf.parse(tf.format(cal.getTime())).getTime());
            } catch (ParseException pe) {

            }
        }

        //FAB - app_bar_main.xml
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        textTimer.setTextColor(ContextCompat.getColor(MainActivity.context, R.color.md_grey_50));
        //tv.setPadding(5, 0, 5, 0);
        textTimer.setTextSize(21);
        menu.add(0, Menu.FIRST + 1, Menu.NONE, "00:00").setActionView(textTimer).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO set hardcoded string as variables
        //TODO move to android:theme
        if (id == R.id.nav_classmates){
            setTitle("Classmates");
            toolbar.setElevation(16);
            ClassmatesFragment classmatesFragment = new ClassmatesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, classmatesFragment).commit();
        } else if (id == R.id.nav_settings){
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_overview){
            toolbar.setElevation(0);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, timetablePageFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startTimer (final long timeInMillis){
        new CountDownTimer(timeInMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                String fillerText;
                if ((millisUntilFinished / 1000) % 60 < 10){
                    fillerText = ":0";
                } else {
                    fillerText = ":";
                }
                textTimer.setText(((millisUntilFinished / (1000*60)) % 60) + fillerText + (millisUntilFinished / 1000) % 60);
            }

            public void onFinish() {
                //TODO lesson fading
                int currentSubject = timetable.getCurrentSubjectID(false);
                if (currentSubject % 2 != 0) {
                    TimetableCardFragment currentPageFragment = timetablePageFragment.generatedFragments.get(timetablePageFragment.tabLayout.getSelectedTabPosition());
                    currentPageFragment.models.get(timetable.getCurrentSubjectID(true) - 1).mLessonDone = true;
                    currentPageFragment.recyclerView.getAdapter().notifyItemChanged(timetable.getCurrentSubjectID(true) - 1);
                }
                if (currentSubject < 18) {
                    try {
                        //Log.e("Main", "Current subject ID is " + timetable.getCurrentSubjectID(false));
                        cal = Calendar.getInstance();
                        startTimer(tf.parse(timetable.times[currentSubject + 1]).getTime() - tf.parse(tf.format(cal.getTime())).getTime());
                    } catch (ParseException pe) {

                    }
                }
            }
        }.start();
    }
}
