package com.ds.cgApp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView alarmSetting;
    private Button inputBtn;
    private Button cancelBtn;
    private TimePicker timePicker;
    private int hour;
    private int minute;
    private String hourString;
    private String minuteString;
    public static String transferData;
    private NotificationHelper mNotificationHelper;
    public static final String TAG = "Alarm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mNotificationHelper = new NotificationHelper(this);

        alarmSetting = (TextView) findViewById(R.id.alarmSetting);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        setButton();
    }


    /**
     * 시간을 정하면 호출되는 메소드
     * @param view 화면
     * @param hourOfDay 시간
     * @param minute 분
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Log.d(TAG, "## onTimeSet ## ");
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        //화면에 시간지정
        updateTimeText(c);

        //알람설정정
        startAlarm(c);
    }

    /**
     * 화면에 사용자가 선택한 시간을 보여주는 메소드
     * @param c 시간
     */
    private void updateTimeText(Calendar c){

        Log.d(TAG, "## updateTimeText ## ");
        String timeText = "알람시간: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        alarmSetting.setText(timeText);
    }

    /**
     * 알람 시작
     * @param c 시간
     */
    private void startAlarm(Calendar c){
        Log.d(TAG, "## startAlarm ## ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        //RTC_WAKE : 지정된 시간에 기기의 절전 모드를 해제하여 대기 중인 인텐트를 실행
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }

    /**
     * 알람 취소
     */
    private void cancelAlarm(){
        Log.d(TAG, "## cancelAlarm ## ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        alarmSetting.setText("알람 취소");
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
                    sendOnChannel("Today Alarm", setTXT);
                    transferData = setTXT;
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                    break;
                case R.id.cancelBtn:
                    String Reset = "No Alarm Setting";
                    cancelAlarm();
                    alarmSetting.setText(Reset);
                    break;
            }
        }
    };

    // Notification Setting
    public void sendOnChannel(String title, String message){
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build());
    }
//
//    //노티피케이션 채널 생성. 오레오버전 이후 부터 노티피케이션채널 생성 필수
//    private void createNotificationChannel(){
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            String channelId = getString (R.string.notification_channel_id);
//            String channelName = getString (R.string.notification_channel_name);
//            String channelDes = getString (R.string.notification_channel_description);
//            NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
//            NotificationChannel notificationChannel =
//                    new NotificationChannel (channelId //채널 ID
//                            , channelName //채널 Name
//                            , NotificationManager.IMPORTANCE_HIGH);//중요도 HIGH 부터 헤드업 알림
//            notificationChannel.setDescription (channelDes);//채널설명
//            notificationManager.createNotificationChannel (notificationChannel);
//        }
//    }
//    //노티피케이션 생성
//    private void createNotification(){
//        String channelId = getString (R.string.notification_channel_id);
//        NotificationCompat.Builder notification =
//                new NotificationCompat.Builder (this, channelId)
//                        .setSmallIcon (R.mipmap.ic_launcher)  //아이콘
//                        .setContentTitle ("Notification") //노티피케이션 타이틀
//                        .setContentText ("Application") //본문 텍스트
//                        .setAutoCancel (true); //사용자가 탭하면 자동으로 알림을 삭제
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
//        notificationManager.notify (0, notification.build ());
//    }
}