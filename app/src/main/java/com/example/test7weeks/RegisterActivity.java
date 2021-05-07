package com.example.test7weeks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {
    FloatingActionButton addUser;
    List<Button> buttons = new ArrayList<>();
    Button Patient;
    public int UserNumber;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bridgeXML();
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,
                        CreateUserActivity.class));

            }
        });
        for (Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this,
                            MainActivity.class));

                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int UserNumber = 0;
        Set<String> names = PreferenceManager.getDefaultSharedPreferences(this).getStringSet("Names", new HashSet<>());
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);
        for (String name: sortedNames){
            if ( UserNumber == 4 )
                break;
            Button b = buttons.get(UserNumber++);
            b.setText(name);
            if (!name.equals("INVISIBLE")){
                b.setVisibility(View.VISIBLE);
            }
            else {
                b.setEnabled(false);

            }
        }
//        for (Button b: buttons){
//            b.setText(this.getButtonText(UserNumber));
//            b.setVisibility(View.VISIBLE);
//            UserNumber++;
//        }
//        Patient.setText(this.getButtonText());
//        Patient.setVisibility(View.VISIBLE);

    }

    public String getButtonText(int UserNumber){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String buttonText = prefs.getString("ButtonText" + UserNumber, "No users found."); //getStringSet
        return buttonText;
    }

    private void bridgeXML() {//setting up the widgets
        int[] a = {R.id.patient1, R.id.patient2, R.id.patient3, R.id.patient4};

        for (int i = 0; i < a.length; i++) {
            Patient = findViewById(a[i]);
            buttons.add(Patient);
        }
        addUser = findViewById(R.id.fab);
    }
}
