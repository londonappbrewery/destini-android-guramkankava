package com.londonappbrewery.destini;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String STORY_TEXT_VIEW_LAST_TEXT = "storyTextViewLastText";
    public static final String CHOICE_A_BUTTON_LAST_TEXT = "choiceAButtonLastText";
    public static final String CHOICE_B_BUTTON_LAST_TEXT = "choiceBButtonLastText";
    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView storyTextView;
    private Button choiceA;
    private Button choiceB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        storyTextView = findViewById(R.id.storyTextView);
        choiceA = findViewById(R.id.buttonTop);
        choiceB = findViewById(R.id.buttonBottom);

        choiceA.setOnClickListener(buttonClickListener);
        choiceB.setOnClickListener(buttonClickListener);

        if(savedInstanceState != null) {
            final String contentForTextView = savedInstanceState.getString(STORY_TEXT_VIEW_LAST_TEXT);
            final String contentForButtonA = savedInstanceState.getString(CHOICE_A_BUTTON_LAST_TEXT);
            final String contentForButtonB = savedInstanceState.getString(CHOICE_B_BUTTON_LAST_TEXT);
            storyTextView.setText(contentForTextView);
            choiceA.setText(contentForButtonA);
            choiceB.setText(contentForButtonB);
        }
        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:




        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:


    }



    private View.OnClickListener buttonClickListener = (View view) -> {
        Log.d("MainActivity", ((Button) view).getText().toString());
        updateStoryScreen(view);
    };

    private void updateStoryScreen(View view) {
        Button button = (Button)view;
        String ans1 = getResources().getString(R.string.T1_Ans1);
        String ans2 =  getResources().getString(R.string.T1_Ans2);

        switch (button.getText().toString()) {
            case "I\'ll hop in. Thanks for the help!":
            case "At least he\'s honest. I\'ll climb in.":
                storyTextView.setText(R.string.T3_Story);
                choiceA.setText(R.string.T3_Ans1);
                choiceB.setText(R.string.T3_Ans2);
                break;
            case "Better ask him if he\'s a murderer first.":
                storyTextView.setText(R.string.T2_Story);
                choiceA.setText(R.string.T2_Ans1);
                choiceB.setText(R.string.T2_Ans2);
                break;
            case "Wait, I know how to change a tire." :
                storyTextView.setText(R.string.T4_End);
                restartApp("Game over", "You survived!");
                break;

            case "I love Elton John! Hand him the cassette tape." :
                storyTextView.setText(R.string.T6_End);
                restartApp("Game over", "You survived! It's on you to call a police or give up");
                break;

            case "It\'s him or me! You take the knife and stab him." :
                storyTextView.setText(R.string.T5_End);
                restartApp("Game over", "You are dead!");
                break;


            default:
                storyTextView.setText(R.string.T1_Story);
                choiceA.setText(R.string.T1_Ans1);
                choiceA.setText(R.string.T1_Ans2);
                break;
        }
    }
    private void restartApp(String endTitle, String endMessage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(endTitle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage(endMessage);
        alertDialogBuilder.setPositiveButton("Close application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartScreen();
            }
        });
        alertDialogBuilder.show();
    }
    public void restartScreen() {
        storyTextView.setText(R.string.T1_Story);
        choiceA.setText(R.string.T1_Ans1);
        choiceB.setText(R.string.T1_Ans2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STORY_TEXT_VIEW_LAST_TEXT, storyTextView.getText().toString());
        outState.putString(CHOICE_A_BUTTON_LAST_TEXT, choiceA.getText().toString());
        outState.putString(CHOICE_B_BUTTON_LAST_TEXT, choiceB.getText().toString());
    }
}
