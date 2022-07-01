package com.ds.cgApp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatchActivity extends AppCompatActivity {

    private TextView minute, second, millsecond;

    private ListView listView;

    private Button start, stop;

    boolean isRunning = false;

    private Thread timeThread = null;

    int time = 0;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> listTime = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        minute = (TextView) findViewById(R.id.minute);
        second = (TextView) findViewById(R.id.Second);
        millsecond =(TextView) findViewById(R.id.millsecond);
        start = (Button) findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
        listView = (ListView) findViewById(R.id.listView);

        start.setOnClickListener(TimerOnClickListener);
        stop.setOnClickListener(TimerOnClickListener);

        start.setEnabled(true);
        stop.setEnabled(false);
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

    private Button.OnClickListener TimerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                        if(isRunning) {
                            run_pause();
                        }else {
                            run_start();
                        }
                    break;
                case R.id.btn_stop:
                        timeThread.interrupt();
                        minute.setText("00");
                        second.setText(":00");
                        millsecond.setText(".00");
                        start.setText("start");
                        start.setEnabled(true);
                        stop.setEnabled(false);
                        time = 0;
                        listTime.clear();
                        listView.clearFocus();
                    break;
            }
        }
    };

    private void run_pause() {
        start.setText("start");
        isRunning = false;
//        Intent intent = new Intent(this,StopWatchService.class);
//        intent.putExtra("isRunning",isRunning);
//        startService(intent);
        timeThread = new Thread(new timeThread());
        timeThread.start();
        try {
            Thread.sleep(20);
        }catch (Exception e){
            e.printStackTrace();
        }
        String time_arr = minute.getText().toString() + second.getText().toString() + millsecond.getText().toString();
        listTime.add(time_arr);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listTime);
        listView.setAdapter(adapter);

    }
    private void run_start() {
        stop.setEnabled(true);
        start.setText("pause");
        isRunning = true;
//        Intent intent = new Intent(this,StopWatchService.class);
//        intent.putExtra("isRunning",isRunning);
//        startService(intent);

        timeThread = new Thread(new timeThread());
        timeThread.start();
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

    class timeThread implements Runnable {
        @Override
        public void run() {
            while (isRunning) { //일시정지를 누르면 멈춤
                Message msg = new Message();
                msg.arg1 = time++;
//                if(time > 100){
//                    isRunning =false;
//                }
                handler.sendMessage(msg);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    minute.setText("00");
                    second.setText(":00");
                    millsecond.setText(".00");
                    return;
                }
            }
        }
    }
}
