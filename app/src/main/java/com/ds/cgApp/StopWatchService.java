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

public class StopWatchService extends Service {
    private Context mContext;

    private TextView minute, second, millsecond;

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
        showToast();
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        isRunning = intent.getBooleanExtra("isRunning",false);
//
//        timeThread = new Thread(new timeThread());
//        timeThread.start();
//        return super.onStartCommand(intent, flags, startId);
        mContext = getApplicationContext();
        return flags;
    }

    void showToast() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    while (time<10){
                        time++;
                        if(time>9){
                            Toast.makeText(mContext, "Display your message here", Toast.LENGTH_SHORT).show();
                        }
                        try {
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            });
    }

    @Override
    public void onDestroy() {
        timeThread.interrupt();
        stopSelf();
        super.onDestroy();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;

            String Time_m = String.format("%02d",min);
            String Time_s = String.format(":%02d",sec);
            String Time_ms = String.format(".%02d",mSec);
            minute.setText(Time_m);
            second.setText(Time_s);
            millsecond.setText(Time_ms);
        }
    };

    private class timeThread implements Runnable {
        @Override
        public void run() {
            while (isRunning) { //일시정지를 누르면 멈춤
                Message msg = new Message();
                msg.arg1 = time++;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
