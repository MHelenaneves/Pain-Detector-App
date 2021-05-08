package com.example.test7weeks;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;

import fr.enssat.caronnantel.ShowResults;

public class MainActivity extends AppCompatActivity {


    private int count = 1;
    private ShowResults showResults;
    private Resources resources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resources = this.getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initThreads(resources);
        try {
            showResults = new ShowResults(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    showResults = new ShowResults(resources);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (count > 16)
                        count = 1;
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String results = showResults.RTstate(count, resources); //we are just testing for file 10
                    System.out.println("results = " + results);

                    if (results.equals("Current state not available"))
                        startActivity(new Intent(MainActivity.this,
                                        ImpedanceCheckActivity.class));

                    else if (results.equals("Pain"))
                        startActivity(new Intent(MainActivity.this,
                                        PainStateActivity.class));

                    else if (results.equals("No Pain")) {
                        startActivity(new Intent(MainActivity.this,
                                NoPainStateActivity.class));
                    }
                    count++;
                    System.out.println("File changed");
                }
            }
        });
        t.start();
    }
}
