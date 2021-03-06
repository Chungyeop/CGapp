package com.ds.cgApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClockMainActivity extends AppCompatActivity {

    private Button clockBtn, stopwatchBtn, alarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_main);

        setButton();
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

    public void setButton() {
        /* Button Setting */
        clockBtn = findViewById(R.id.clockBtn);
        stopwatchBtn = (Button) findViewById(R.id.stopwatchBtn);
        alarmBtn = (Button) findViewById(R.id.alarmBtn);
        /* Button ClickListener */
        clockBtn.setOnClickListener(btnClickListener);
        stopwatchBtn.setOnClickListener(btnClickListener);
        alarmBtn.setOnClickListener(btnClickListener);
    }

    /* Button ClickListener Case */
    private final Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /* Button Action */
            switch(view.getId()) {
                case R.id.clockBtn:
                    Intent clockMainIntent = new Intent(getApplicationContext(), ClockActivity.class);
                    clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(clockMainIntent);
                    break;
                case R.id.stopwatchBtn:
                    Intent stopwatchIntent = new Intent(getApplicationContext(),StopWatchActivity.class);
                    stopwatchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    stopwatchIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    stopwatchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(stopwatchIntent);
                    break;
                case R.id.alarmBtn:
                    Intent alarmIntent = new Intent(getApplicationContext(),AlarmActivity.class);
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(alarmIntent);
                    break;
            }
        }
    };
}