package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private RelativeLayout mainScreen;
    private TextView storyTextView;
    private Button buttonTop;
    private Button buttonBottom;
    private int clickIndex = 1;

    private final View.OnClickListener onScreenClickListener = (View view) -> {
        setToInitialState();
    };

    private void setToInitialState() {
        clickIndex = 1;
        storyTextView.setText(R.string.T1_Story);
        buttonTop.setText(R.string.T1_Ans1);
        buttonTop.setVisibility(View.VISIBLE);
        buttonBottom.setText(R.string.T1_Ans2);
        buttonBottom.setVisibility(View.VISIBLE);
        mainScreen.setOnClickListener(null);
    }

    private final View.OnClickListener onButtonClickListener = (View view) -> {
        Button button = (Button)view;

        switch(button.getId()) {
            case R.id.buttonTop : topButtonHandler(view); break;
            case R.id.buttonBottom : bottomButtonHandler(view); break;
            default : Log.d("MainActivity", "button click unknown");  break;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        storyTextView = findViewById(R.id.storyTextView);
        buttonTop = findViewById(R.id.buttonTop);
        buttonBottom = findViewById(R.id.buttonBottom);
        buttonTop.setOnClickListener(onButtonClickListener);
        buttonBottom.setOnClickListener(onButtonClickListener);
        mainScreen = findViewById(R.id.mainScreen);
    }

    private void topButtonHandler(View view) {
        if(clickIndex == 1 || clickIndex == 2) {
            storyTextView.setText(R.string.T3_Story);
            buttonTop.setText(R.string.T3_Ans1);
            buttonBottom.setText(R.string.T3_Ans2);
            clickIndex = 3;
        } else {
            storyTextView.setText(R.string.T6_End);
            notifyUserForEndResult("You survived the killer, but your conscious is threatened! \nClick to restart");
            buttonTop.setVisibility(View.GONE);
            buttonBottom.setVisibility(View.GONE);
            mainScreen.setOnClickListener(onScreenClickListener);
        }
    }

    private void bottomButtonHandler(View view) {
        if(clickIndex == 1) {
            storyTextView.setText(R.string.T2_Story);
            buttonTop.setText(R.string.T2_Ans1);
            buttonBottom.setText(R.string.T2_Ans2);
            clickIndex = 2;
        } else if(clickIndex == 2) {
            storyTextView.setText(R.string.T4_End);
            notifyUserForEndResult("Wise choice, you won the game! \nClick to restart");
            buttonTop.setVisibility(View.GONE);
            buttonBottom.setVisibility(View.GONE);
            mainScreen.setOnClickListener(onScreenClickListener);
        } else {
            storyTextView.setText(R.string.T5_End);
            notifyUserForEndResult("Brave of you, but you died, game over. \nClick to restart");
            buttonTop.setVisibility(View.GONE);
            buttonBottom.setVisibility(View.GONE);
            mainScreen.setOnClickListener(onScreenClickListener);
        }
    }

    private void notifyUserForEndResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
