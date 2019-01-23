package com.bottomsheetsample;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout bottomSheetOne, bottomSheetTwo;
    BottomSheetBehavior sheetBehavior;
    AppCompatButton buttonOne, buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = (AppCompatButton) findViewById(R.id.buttonOne);
        buttonTwo = (AppCompatButton) findViewById(R.id.buttonTwo);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState()==3){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        bottomSheetOne = (LinearLayout) findViewById(R.id.bottomSheetOne);
        bottomSheetTwo = (LinearLayout) findViewById(R.id.bottomSheetTwo);

        bottomSheetOne.setTranslationY(getStatusBarHeight());
        bottomSheetTwo.setTranslationY(getStatusBarHeight());

        sheetBehavior = BottomSheetBehavior.from(bottomSheetOne);
        sheetBehavior = BottomSheetBehavior.from(bottomSheetTwo);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                Log.d("MainActivity", "onStateChanged: " + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.d("MainActivity", "onSlide: ");
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}