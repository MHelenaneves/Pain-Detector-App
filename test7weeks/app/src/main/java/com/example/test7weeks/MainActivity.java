package com.example.test7weeks;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import fr.enssat.caronnantel.ShowResults;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int count = 1;
    Handler handler;
    ShowResults showResults;
    Resources resources;


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

                    String results = showResults.RTstate(count, resources); //we are just testing for file 10
                    System.out.println("results = " + results);
                    if (results.equals("Current state not available"))
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setContentView(R.layout.activity_statenotfound);
                            } //data not available
                        });

                    else if (results.equals("Pain"))
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this,
                                        PainStateActivity.class));
                            }
                        });

                    else if (results.equals("No Pain")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this,
                                        NoPainStateActivity.class));

                            }
                        });

                    }
                    count++;
                    System.out.println("File changed");
                    //System.out.println("changed from" + --count + " to " + "  " );
                }
            }
        });
        t.start();



        //setContentView(R.layout.activity_main);
        /*handler = new Handler() {
            public void content(Resources resources) {

                count++;
                if (count > 16)
                    count = 1;
                String results = showResults.RTstate(count, resources); //we are just testing for file 10
                if (results == null)
                    setContentView(R.layout.activity_main);
                else if (results.equals("Pain"))
                    setContentView(R.layout.activity_pain);
                else if (results.equals("No Pain")) {
                    setContentView(R.layout.activity_nopain);


                    refresh(0); //change later to what we need
                }
            }
            private void refresh(int seconds){
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        content(resources);
                    }
                };
                handler.postDelayed(runnable, seconds);
            }

        };*/


        //from here to the end is an attempt to refresh every 2.5 min; how can I make this related to the rest?

        }





//
//        try {
//            showResults = new ShowResults(resources);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while (true) {
//            if (count > 16)
//                count = 1;
//            String results = showResults.RTstate(count, resources); //we are just testing for file 10
//            if (results == null)
//                setContentView(R.layout.activity_main);
//            else if (results.equals("Pain"))
//                setContentView(R.layout.activity_pain);
//            else if (results.equals("No Pain")) {
//                setContentView(R.layout.activity_nopain);
//            }
//        }

        /*super.onCreate(savedInstanceState);
        String results = null;
        try {
            results = new ShowResults(resources).RTstate(count, resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (results == null)
            setContentView(R.layout.activity_main );
        else if (results.equals("Pain"))
            setContentView(R.layout.activity_pain);
        else if (results.equals("No Pain")){
            setContentView(R.layout.activity_nopain);
        }*/

/*    private void initThreads(Resources resources) {

        new Thread(new Runnable() {
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
                    String results = showResults.RTstate(count, resources); //we are just testing for file 10
                    if (results == null)
                        setContentView(R.layout.activity_main);
                    else if (results.equals("Pain"))
                        setContentView(R.layout.activity_pain);
                    else if (results.equals("No Pain")) {
                        setContentView(R.layout.activity_nopain);
                    }
                }
            }
        }).start();

    }*/





}
