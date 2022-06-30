package com.ds.cgApp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

public class ClockActivity extends AppCompatActivity {

    private View handSecond = null ;
    private View handMinute = null  ;
    private View handHour = null  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        handSecond = findViewById(R.id.handSecond) ;
        handMinute = findViewById(R.id.handMinute) ;
        handHour = findViewById(R.id.handHour) ;

        handSecond.setPivotX(0.0f) ;
        handSecond.setPivotY(0.0f) ;
        handMinute.setPivotX(0.0f) ;
        handMinute.setPivotY(0.0f) ;
        handHour.setPivotX(0.0f) ;
        handHour.setPivotY(0.0f) ;

        final Handler handler = new Handler() ;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Calendar time = Calendar.getInstance();
                int second = time.get(Calendar.SECOND);
                int minute = time.get(Calendar.MINUTE);
                int hour = time.get(Calendar.HOUR);

                Log.v("Log Check","Log second - " + second);
                Log.v("Log Check","Log minute - " + minute);
                Log.v("Log hour","Log hour - " + hour);

                // Set the rotation of the view.
                handSecond.setRotation(360 * second / 60 - 90) ;
                handMinute.setRotation(360 * minute / 60 - 90) ;
                // handHour.setRotation(360 * hour / 12 - 90);
                handHour.setRotation(360 * ((hour%12)*60+minute)/(12*60) - 90) ;

                handler.postDelayed(this, 1000);
            }
        } ;

        handler.postDelayed(runnable, 1000) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}