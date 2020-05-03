package com.example.shopohop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.System.in;

public class user_signup extends AppCompatActivity {
    EditText et_name,et_email,et_pass,et_cp,et_phone;
    TextView tv_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_pass=(EditText)findViewById(R.id.et_pass);
        et_cp=(EditText)findViewById(R.id.et_cp);
        tv_error=(TextView)findViewById(R.id.tv_error);
        et_cp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_error.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void signup(View v){

        if(!et_pass.getText().toString().equals(et_cp.getText().toString())){
            tv_error.setText("Password and confirm password do not match");
            et_cp.setText("");
            et_cp.setFocusable(true);
        }
        else {
            new Thread(new Job()).start();

        }
    }
    class Job implements Runnable{
        @Override
        public void run(){
            try {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String phone = et_phone.getText().toString();
                String pass = et_pass.getText().toString();

                final String urlpath = serverpath.SERVERPATH+ "user_signup?email=" + email + "&name=" + name + "&phone=" + phone + "&pass=" + pass;
                URL url = new URL(urlpath);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while (true){
                        String s= br.readLine();
                        if(s==null) break;
                        sb.append(s);
                    }
                    String ans=sb.toString();
                    if(ans.equals("success")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Thank you for Signing up",Toast.LENGTH_SHORT).show();
                            }
                        });
                        SharedPreferences sp = getSharedPreferences("mypref",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("user_email",email);
                        editor.commit();
                        finish();   //will not show signup screen on back press

                        Intent in=new Intent(getApplicationContext(),user_home.class);
                        startActivity(in);
                    }else if(ans.equals("email exists")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_error.setText("This email id is already registered. Try Signing up with another email.");
                            }
                        });
                    }
                    else{
                        Log.d("SHOPOHOP","output recieived : "+ans);
                    }

                }else if(rescode==HttpURLConnection.HTTP_NOT_FOUND){
                    Log.d("SHOPOHOP","404 NOT FOUND : "+urlpath);
                }else {
                    Log.d("SHOPOHOP",rescode+"");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
