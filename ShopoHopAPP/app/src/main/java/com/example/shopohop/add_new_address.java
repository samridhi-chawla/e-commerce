package com.example.shopohop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.dial;

public class add_new_address extends AppCompatActivity {
    EditText dialog_address,dialog_city,dialog_state,dialog_pincode;
    Button dialog_bt_add;
    TextView dialog_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        dialog_address=(EditText)findViewById(R.id.dialog_address);
        dialog_city=(EditText)findViewById(R.id.dialog_city);
        dialog_state=(EditText)findViewById(R.id.dialog_state);
        dialog_pincode=(EditText)findViewById(R.id.dialog_pincode);
        dialog_bt_add=(Button)findViewById(R.id.dialog_bt_add);
        dialog_error=(TextView)findViewById(R.id.dialig_error);
        dialog_bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog_address.getText().toString().isEmpty() ||dialog_city.getText().toString().isEmpty() ||dialog_state.getText().toString().isEmpty() ||dialog_pincode.getText().toString().isEmpty()){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog_error.setText("All fields are mandatory");
                    }
                });
                }
                else {
                    new Thread(new save_address_to_db()).start();
                }
            }
        });

    }
    class save_address_to_db implements Runnable{

        @Override
        public void run() {
            try {
                String address = URLEncoder.encode(dialog_address.getText().toString(), "UTF-8");
                String city = URLEncoder.encode(dialog_city.getText().toString(),"UTF-8");
                String state = URLEncoder.encode(dialog_state.getText().toString(),"UTF-8");
                String pincode = URLEncoder.encode(dialog_pincode.getText().toString(),"UTF-8");
                Log.d("SHOPOHOP",address);
                String urlpath = serverpath.SERVERPATH + "add_user_address?email=" + serverpath.USER_EMAIL + "&address=" + address + "&city=" + city + "&state=" + state + "&pincode=" + pincode;
                URL url=new URL(urlpath);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb=new StringBuffer();
                    while (true){ Log.d("SHOPOHOP","HLHLgvgygku");
                        String s=br.readLine();
                        if(s==null)break;
                        sb.append(s);
                    }
                    if(sb.toString().equals("success")){
                        Intent in= new Intent(getApplicationContext(),checkout.class);
                        finish();
                        startActivity(in);
                    }
                }else{
                    Log.d("SHOPOHOP",""+rescode);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
