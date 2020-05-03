package com.example.shopohop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;

public class checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        SharedPreferences sp=getSharedPreferences("mypref",MODE_PRIVATE);
        String user_email=sp.getString("user_email","");
        if(user_email.isEmpty()){
            Intent in = new Intent(this,user_login_before_chechout.class);
            startActivity(in);
        }
        else {
            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.add(R.id.activity_checkout,new place_order_frag6());
            ft.commit();
        }
    }
}
