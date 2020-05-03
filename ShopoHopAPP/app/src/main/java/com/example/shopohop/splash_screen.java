package com.example.shopohop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class splash_screen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 4; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SharedPreferences sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
                serverpath.USER_EMAIL=sharedPreferences.getString("user_email","");
                finish();
                if(serverpath.USER_EMAIL.isEmpty()) {
                    Intent in = new Intent(getApplicationContext(), user_login.class);
                    startActivity(in);
                }else{
                    Intent in=new Intent(getApplicationContext(),user_home.class);
                    startActivity(in);
                }
            }
        }).start();
    }
}