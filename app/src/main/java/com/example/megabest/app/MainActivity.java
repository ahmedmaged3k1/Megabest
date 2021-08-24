package com.example.megabest.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.megabest.R;

public class MainActivity extends AppCompatActivity {
    Intent loginActivity;
    float x1Position, y1Position , x2Position , y2Position;
    ViewGroup swipeView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_UP:
            {
                x1Position = touchEvent.getX();
                y1Position = touchEvent.getY();
                break;
            }

            case MotionEvent.ACTION_DOWN:
            {
                x2Position = touchEvent.getX();
                y2Position = touchEvent.getY();
                break;
            }
        }
        if(y1Position  < y2Position){
            loginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }
        return false;
    }
}