package com.ds.cgApp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener {

    private Button changeBtn;
    private View handSecond, handMinute, handHour;
    private TextView number_One, number_Two, number_Three, number_Four, number_Five, number_Six
            , number_Seven, number_Eight, number_Nine, number_Ten, number_Eleven, number_Twelve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clock);

        number_One = findViewById(R.id.number_One);
        number_Two = findViewById(R.id.number_Two);
        number_Three = findViewById(R.id.number_Three);
        number_Four = findViewById(R.id.number_Four);
        number_Five = findViewById(R.id.number_Five);
        number_Six = findViewById(R.id.number_Six);
        number_Seven = findViewById(R.id.number_Seven);
        number_Eight = findViewById(R.id.number_Eight);
        number_Nine = findViewById(R.id.number_Nine);
        number_Ten = findViewById(R.id.number_Ten);
        number_Eleven = findViewById(R.id.number_Eleven);
        number_Twelve = findViewById(R.id.number_Twelve);

        handSecond = findViewById(R.id.handSecond);
        handMinute = findViewById(R.id.handMinute);
        handHour = findViewById(R.id.handHour);
        changeBtn = findViewById(R.id.changeBtn);

        changeBtn.setOnClickListener(this);

        /* setPivot - 회전 축을 정해주는 Method */
        handSecond.setPivotX(0.0f);
        handSecond.setPivotY(0.0f);
        handMinute.setPivotX(0.0f);
        handMinute.setPivotY(0.0f);
        handHour.setPivotX(0.0f);
        handHour.setPivotY(0.0f);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Calendar time = Calendar.getInstance();
                int second = time.get(Calendar.SECOND);
                int minute = time.get(Calendar.MINUTE);
                int hour = time.get(Calendar.HOUR);

                Log.v("Log Check", "Log second - " + second);
                Log.v("Log Check", "Log minute - " + minute);
                Log.v("Log hour", "Log hour - " + hour);

                // Set the rotation of the view.
                handSecond.setRotation(360 * second / 60 - 90);
                handMinute.setRotation(360 * minute / 60 - 90);
                // handHour.setRotation(360 * hour / 12 - 90);
                handHour.setRotation(360 * ((hour % 12) * 60 + minute) / (12 * 60) - 90);

                handler.postDelayed(this, 1000);
            }
        };
        //딜레이가 딱히 필요업서보여서 postDelayed -> post 변경했습니다.
        handler.post(runnable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeBtn:
                if (changeBtn.getText().toString().equals("ChangeR")) {
                    changeBtn.setText("ChangeN");
                    number_One.setText("Ⅰ");
                    number_Two.setText("Ⅱ");
                    number_Three.setText("Ⅲ");
                    number_Four.setText("Ⅳ");
                    number_Five.setText("Ⅴ");
                    number_Six.setText("Ⅵ");
                    number_Seven.setText("Ⅶ");
                    number_Eight.setText("Ⅷ");
                    number_Nine.setText("Ⅸ");
                    number_Ten.setText("Ⅹ");
                    number_Eleven.setText("XI");
                    number_Twelve.setText("XII");
                } else {
                    changeBtn.setText("ChangeR");
                    number_One.setText("1");
                    number_Two.setText("2");
                    number_Three.setText("3");
                    number_Four.setText("4");
                    number_Five.setText("5");
                    number_Six.setText("6");
                    number_Seven.setText("7");
                    number_Eight.setText("8");
                    number_Nine.setText("9");
                    number_Ten.setText("10");
                    number_Eleven.setText("11");
                    number_Twelve.setText("12");
                }
                break;
        }
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