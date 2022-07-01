package com.ds.cgApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TextView alarmSetting;
    private Button inputBtn;
    private Button cancelBtn;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmSetting = (TextView) findViewById(R.id.alarmSetting);
        inputBtn = (Button) findViewById(R.id.inputBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        inputBtn.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            // TimePicker.getHour() / TimePicker.getMinute() - API 23 이상에서만 지원
            if(Build.VERSION.SDK_INT < 23){
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
            } else {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());
            }


        });

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