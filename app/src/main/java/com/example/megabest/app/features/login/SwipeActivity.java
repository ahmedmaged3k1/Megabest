package com.example.megabest.app.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.megabest.R;

public class SwipeActivity extends AppCompatActivity {
    Intent loginActivity;
    float x1Position, y1Position, x2Position, y2Position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_UP: {
                x1Position = touchEvent.getX();
                y1Position = touchEvent.getY();
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                x2Position = touchEvent.getX();
                y2Position = touchEvent.getY();
                break;
            }
        }
        if (y1Position < y2Position) {
            loginActivity = new Intent(SwipeActivity.this, LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }
        return false;
    }
}