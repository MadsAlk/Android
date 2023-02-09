package com.example.travelguide.all;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class MyOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.setBackgroundColor(Color.LTGRAY);
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            v.setBackgroundColor(Color.WHITE);
        }
        return false;
    }
}
