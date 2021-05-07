package com.example.test7weeks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PainStateActivity extends AppCompatActivity {

AppCompatButton BrowseEEG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_pain_state);
        bridgeXML();
        BrowseEEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PainStateActivity.this,
                        EEGSignalsActivity.class));

            }
        });
    }

    private void bridgeXML() {
        BrowseEEG = findViewById(R.id. browseEEG);
    }
}
