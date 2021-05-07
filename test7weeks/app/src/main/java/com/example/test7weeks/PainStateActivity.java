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

public class PainStateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView Circle;
    AnimatedVectorDrawableCompat avdCircle;
    AppCompatButton BrowseEEG;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_state);
        bridgeXML();
        signal_an();
        Name.setText(this.getTextViewText());
        setOnClickListeners();

    }

    public String getTextViewText(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String TextViewName = prefs.getString("TextViewText", "Patient1");
        return TextViewName;
    }

    public void onClick(View v) {
        BrowseEEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PainStateActivity.this,
                        EEGSignalsActivity.class));

            }
        });
    }
    private void signal_an() {
        Circle = findViewById(R.id.circle_iconpain);
        Drawable drawable = Circle.getDrawable();
        //If(drawable instanceof  AnimatedVectorDrawableCompat){
        avdCircle =(AnimatedVectorDrawableCompat) drawable;
        avdCircle.start();
        //} else if(drawable instanceof AnimatedVectorDrawable){
        //avdLauncher2 = (AnimatedVectorDrawable)  drawable;
        // avdLauncher2.start();
    }

    private void setOnClickListeners() {
        BrowseEEG.setOnClickListener(PainStateActivity.this);
    }

    private void bridgeXML() {

        BrowseEEG = findViewById(R.id. browseeegpain);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
