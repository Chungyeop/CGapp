package com.ds.cgApp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.MainThread;

public class StopWatchService extends Service {

    boolean isRunning = false;

    int time = 0;

    private Thread timeThread = null;



    public StopWatchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        timeThread.interrupt();
        stopSelf();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            return Service.START_STICKY;
        }else{
            processCommand(intent, flags, startId);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent, int flags, int startId){
        isRunning = intent.getBooleanExtra("isRunning",false);
        time = intent.getIntExtra("time",0);
        Log.d(TAG,"isRunning = " + isRunning);
        Log.d(TAG,"time = " + time);
        showToast();
    }

    private void showToast() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Handler mHandler = new Handler(Looper.getMainLooper());
                while (isRunning) {
                    time++;
                    Log.d(TAG, "time : == " + time);
                    // 5초
                    if (time == 500) {
                        //Thread에서 다른 Activity에 Toast를 띄우기 위해 handler로 Toast를 감쌉니다.
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Service 00:05.00",Toast.LENGTH_SHORT).show();
                            }
                        });
                        //5초에 종료
                        isRunning = false;
                    }
                    if(time==1){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Start",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
