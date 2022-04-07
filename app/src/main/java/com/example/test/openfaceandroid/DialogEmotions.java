package com.example.test.openfaceandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Switch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DialogEmotions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_emotions);
    }

    public void btnSubmit_onClick(View v) {
        Log.d("DialogEmotions","btn listener working");
        RadioButton swAngry = (RadioButton) findViewById(R.id.swAngry);
        RadioButton swDigust = (RadioButton) findViewById(R.id.swDisgusted);
        RadioButton swFeared = (RadioButton) findViewById(R.id.swFeared);
        RadioButton swHappy = (RadioButton) findViewById(R.id.swHappy);
        RadioButton swNeutral = (RadioButton) findViewById(R.id.swNeutral);
        RadioButton swSad = (RadioButton) findViewById(R.id.swSad);
        RadioButton swSurprised = (RadioButton) findViewById(R.id.swSurprised);

        int ratingAngry = swAngry.isChecked() ? 1 : 0;
        int ratingDisgust = swDigust.isChecked() ? 1 : 0;
        int ratingFeared = swFeared.isChecked() ? 1 : 0;
        int ratingHappy = swHappy.isChecked() ? 1 : 0;
        int ratingNeutral = swNeutral.isChecked() ? 1 : 0;
        int ratingSad = swSad.isChecked() ? 1 : 0;
        int ratingSurprised = swSurprised.isChecked() ? 1 : 0;
        String logRes = ratingAngry + ";"
                        + ratingDisgust + ";"
                        + ratingFeared + ";"
                        + ratingHappy + ";"
                        + ratingNeutral + ";"
                        + ratingSad + ";"
                        + ratingSurprised;

        Logging.appendLog(logRes, Logging.LOG_FILE_USER_EMOTIONRATING, true, true);
        finish();
    }
}
