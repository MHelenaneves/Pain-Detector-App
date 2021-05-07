package com.example.test7weeks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

public class NoPainStateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView Circle;
    AnimatedVectorDrawableCompat avdCircle;
    AppCompatButton BrowseEEG;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_pain_state);
        bridgeXML();
        signal_an();
        Name.setText(this.getTextViewText());
        setOnClickListeners();
    }

    public String getTextViewText(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String TextViewName = prefs.getString("TextPatientName", "Patient1");
        return TextViewName;
    }

    public void onPause() {
        super.onPause();

    }

    public void onClick(View v) {
        BrowseEEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoPainStateActivity.this,
                        EEGSignalsActivity.class));

            }
        });
    }

    private void signal_an() {
        Circle = findViewById(R.id.circle_icon);
        Drawable drawable = Circle.getDrawable();
        //If(drawable instanceof  AnimatedVectorDrawableCompat){
        avdCircle =(AnimatedVectorDrawableCompat) drawable;
        avdCircle.start();
        //} else if(drawable instanceof AnimatedVectorDrawable){
        //avdLauncher2 = (AnimatedVectorDrawable)  drawable;
        // avdLauncher2.start();
    }
    private void bridgeXML() {
        BrowseEEG = findViewById(R.id.browseeegnopain);
        Name = findViewById(R.id.namenopain);
    }

    private void setOnClickListeners() {
        BrowseEEG.setOnClickListener(NoPainStateActivity.this);
        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
