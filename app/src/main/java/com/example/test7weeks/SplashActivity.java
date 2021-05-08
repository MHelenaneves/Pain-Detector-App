 package com.example.test7weeks;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

public class SplashActivity extends AppCompatActivity {

    // Sets splash screen time in miliseconds
    private static int SPLASH_TIME = 2000;
    ImageView launcherIcon;
    AnimatedVectorDrawableCompat avdLauncher;
    AnimatedVectorDrawable avdLauncher2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        signal_an();
                new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                // run() method will be executed when 3 seconds have passed
                //Time to start RegisterActivity

                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void signal_an() {
        launcherIcon = findViewById(R.id.imageView2);
        Drawable drawable = launcherIcon.getDrawable();
        //If(drawable instanceof  AnimatedVectorDrawableCompat){

            if (drawable instanceof Animatable)
            {
                ((Animatable) drawable).start();
            }

        //} else if(drawable instanceof AnimatedVectorDrawable){
           //avdLauncher2 = (AnimatedVectorDrawable)  drawable;
           // avdLauncher2.start();
        }
    //}


}
