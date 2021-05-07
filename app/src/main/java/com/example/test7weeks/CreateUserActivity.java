package com.example.test7weeks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.ArraySet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateUserActivity extends Activity implements View.OnClickListener {

    TextView FirstName, MedicalCondition, Medication, Comments;
    EditText FirstNameEntry, MedicalConditionEntry, MedicationEntry, CommentsEntry;
    AppCompatButton save,confirm;
    static int UserNumber = 0;
    Set<String> names = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);
        bridgeXML();
        setOnClickListeners();
        save.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        names = prefs.getStringSet("Names", new HashSet<>());
        try {
            editor.putString("ButtonText" + UserNumber, new ArrayList<String>(names).get(UserNumber));
        }
        catch (IndexOutOfBoundsException e){
            editor.putString("ButtonText" + UserNumber, "INVISIBLE");

        }
        editor.commit();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                Toast.makeText(CreateUserActivity.this, "Confirmed", Toast.LENGTH_SHORT).show();
                save.setVisibility(View.VISIBLE);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

                SharedPreferences.Editor editor = prefs.edit();
                names = prefs.getStringSet("Names", new HashSet<>());
                names.add(FirstNameEntry.getText().toString());
                editor.putStringSet("Names", names);
                editor.commit();
                break;
            case R.id.save:
                //implement this part
                // save button should lead to register activity and create a button there with the
                // name of the entries of edittexts firstname and lastname


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CreateUserActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                });
                Toast.makeText(CreateUserActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }


    private void setOnClickListeners() {
        save.setOnClickListener(CreateUserActivity.this);
        confirm.setOnClickListener(CreateUserActivity.this);}

    private void bridgeXML() {//setting up the widgets
        FirstName = findViewById(R.id.firstname);

        MedicalCondition = findViewById(R.id.medicalcondition);
        Medication = findViewById(R.id.medication);
        Comments = findViewById(R.id.comments);
        FirstNameEntry = findViewById(R.id.editFirstName);

        MedicalConditionEntry = findViewById(R.id.editMedicalCondition);
        MedicationEntry = findViewById(R.id.editMedication);
        CommentsEntry = findViewById(R.id.editComments);
        save = findViewById(R.id.save);
        confirm = findViewById(R.id.confirm);
    }



}
