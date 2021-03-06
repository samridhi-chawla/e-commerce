package com.example.shopohop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class user_login extends AppCompatActivity {
    EditText et1, et2;
    TextView tv_error, tv_sign_up;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        tv_error = (TextView) findViewById(R.id.tv_error);
        tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),user_signup.class);
                startActivity(in);
            }
        });


    }


    public void login(View v) {
        new Thread(new Job()).start();
    }

    public void go_to_home(View v) {
        finish();
        Intent in = new Intent(this, user_home.class);
        startActivity(in);
    }
    class Job implements Runnable {
        public void run() {
            try {

                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                final String urlpath = serverpath.SERVERPATH+"check_user_login?email=" + email + "&password=" + pass;

                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                int resCode = connection.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String s = br.readLine();
                        if (s == null) {
                            break;
                        }
                        sb.append(s);
                    }
                    String ans = sb.toString();
                    if (ans.equals("success")) {
                        //store the email in shared pref
                        SharedPreferences sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("user_email",email);
                        editor.commit();
                        finish();
                        Intent in = new Intent(getApplicationContext(), user_home.class);
                        startActivity(in);
                    } else if (ans.equals("fail")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_error.setText("Invalid Email or Password. Please try again.");
                                et2.setText("");
                                et2.setFocusable(true);
                            }
                        });
                    }
                } else if (resCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    Log.d("SHOPOHOP", "404 NOT FOUND URL: " + urlpath);
                } else {
                    Log.d("SHOPOHOP", resCode + "");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
