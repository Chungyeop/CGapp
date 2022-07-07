package com.ds.cgApp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String noteID = "noteID";
    public static final String noteName = "noteName";

    private NotificationManager notifyManager;

    public NotificationHelper(Context base) {
        super(base);

        // Android Version Oreo 이상에서는 반드시 Channel 생성 필요
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(){

        NotificationChannel noteChannel = new NotificationChannel(noteID, noteName, NotificationManager.IMPORTANCE_DEFAULT);
        noteChannel.enableLights(true);
        noteChannel.enableVibration(true);
        noteChannel.setLightColor(R.color.primary);
        noteChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(noteChannel);
    }

    public NotificationManager getManager(){
        if(notifyManager == null){
            notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notifyManager;
    }

    public NotificationCompat.Builder getChannelNotification(){

        return new NotificationCompat.Builder(getApplicationContext(), noteID)
                .setContentTitle("Alarm")
                .setContentText("Wake Up!")
                .setVibrate(new long[]{3000, 100, 3000, 100})
                .setSmallIcon(R.drawable.alarm_clock);
    }
}