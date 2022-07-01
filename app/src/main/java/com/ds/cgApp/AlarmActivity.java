package com.ds.cgApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private int hour;
    private int minute;
    private String hourString;
    private String minuteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        createNotificationChannel ();

        alarmSetting = (TextView) findViewById(R.id.alarmSetting);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

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
        inputBtn = findViewById(R.id.inputBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        /* Button ClickListener */
        inputBtn.setOnClickListener(btnClickListener);
        cancelBtn.setOnClickListener(btnClickListener);
    }

    /* Button ClickListener Case */
    private final Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /* Button Action */
            switch (view.getId()) {
                case R.id.inputBtn:
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    createNotification ();
                    // TimePicker.getHour() / TimePicker.getMinute() - API 23 이상에서만 지원
                    if (Build.VERSION.SDK_INT < 23) {
                        hour = timePicker.getCurrentHour();
                        minute = timePicker.getCurrentMinute();

                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    } else {
                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();

                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                        calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    }
                    if (hour < 10){
                        hourString = "0" + Integer.toString(hour);
                    } else {
                        hourString = Integer.toString(hour);
                    }
                    if (minute < 10){
                        minuteString = "0" + Integer.toString(minute);
                    } else {
                        minuteString = Integer.toString(minute);
                    }
                    String setTXT = "Alarm " + hourString + " : " + minuteString;
                    alarmSetting.setText(setTXT);
                    break;
                case R.id.cancelBtn:
                    String Reset = "No Alarm Setting";
                    alarmSetting.setText(Reset);
                    break;
            }
        }
    };
    //노티피케이션 채널 생성. 오레오버전 이후 부터 노티피케이션채널 생성 필수
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String channelId = getString (R.string.notification_channel_id);
            String channelName = getString (R.string.notification_channel_name);
            String channelDes = getString (R.string.notification_channel_description);
            NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    new NotificationChannel (channelId //채널 ID
                            , channelName //채널 Name
                            , NotificationManager.IMPORTANCE_HIGH);//중요도 HIGH 부터 헤드업 알림
            notificationChannel.setDescription (channelDes);//채널설명
            notificationManager.createNotificationChannel (notificationChannel);
        }
    }
    //노티피케이션 생성
    private void createNotification(){
        String channelId = getString (R.string.notification_channel_id);
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder (this, channelId)
                        .setSmallIcon (R.mipmap.ic_launcher)  //아이콘
                        .setContentTitle ("Test") //노티피케이션 타이틀
                        .setContentText ("Testing") //본문 텍스트
                        .setAutoCancel (true); //사용자가 탭하면 자동으로 알림을 삭제

        NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
        notificationManager.notify (0, notification.build ());
    }
}