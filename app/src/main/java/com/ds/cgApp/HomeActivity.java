package com.ds.cgApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        Button clockBtn = (Button) findViewById(R.id.clockBtn);

        /* Button ClickListener */
        clockBtn.setOnClickListener(btnClickListener);
    }

    /* Button ClickListener Case */
    private final Button.OnClickListener btnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            /* Button Action */
            if (view.getId() == R.id.clockBtn) {
                Intent clockMainIntent = new Intent(getApplicationContext(), ClockActivity.class);
                clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                clockMainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        }
    };
}