package com.ds.cgApp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.time.LocalTime;

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {

                LocalTime time = LocalTime.now() ;
                int second = time.getSecond() ;
                int minute = time.getMinute() ;
                int hour = time.getHour() ;

                System.out.println("hour    : " + hour) ;
                System.out.println("minute  : " + minute) ;
                System.out.println("second  : " + second) ;

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