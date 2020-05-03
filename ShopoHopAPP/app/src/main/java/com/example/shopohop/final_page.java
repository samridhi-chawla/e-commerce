package com.example.shopohop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class final_page extends AppCompatActivity {
Button bt_continue_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        new Thread(new set_order()).start();
        bt_continue_shop=(Button)findViewById(R.id.bt_continue_shop);
        bt_continue_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),user_home.class);
                finish();
                startActivity(in);
            }
        });
    }
    class set_order implements Runnable{

        @Override
        public void run() {
            try {Log.d("SHOPOHOP","GERTYT");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", serverpath.USER_EMAIL);
                jsonObject.put("add_id", serverpath.ADDRESS_ID);
                //jsonObject.put("add_id", "1");
                jsonObject.put("net_amt", serverpath.CART_TOTAL);
                jsonObject.put("vat", serverpath.VAT);
                jsonObject.put("taxes", serverpath.TAXES);
                jsonObject.put("delivery_charges", serverpath.DELIVERY_CHARGES);
                jsonObject.put("net_payable_amt", serverpath.NET_PAYABLE_AMT);
                jsonObject.put("payment_mode", serverpath.PAYMENT_MODE);
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<serverpath.al_cart.size();i++){
                    JSONObject jsonObject1 = new JSONObject();
                    //cart_item item=serverpath.al_cart.get(i);
                    jsonObject1.put("p_name",serverpath.al_cart.get(i).p_name);
                    jsonObject1.put("p_id",serverpath.al_cart.get(i).p_id);
                    jsonObject1.put("p_offer_price",serverpath.al_cart.get(i).p_offer_price);
                    jsonObject1.put("qty",serverpath.al_cart.get(i).qty);
                    jsonObject1.put("p_photo",serverpath.al_cart.get(i).p_photo);
                    jsonArray.put(jsonObject1);
                }

                jsonObject.put("al_cart", jsonArray);


Log.d("SHOPOHOP",jsonObject.toString());
                String urlpath=serverpath.SERVERPATH+"user/mobile_final.jsp";
                URL url=new URL(urlpath);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(jsonObject.toString());

                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb=new StringBuffer();
                    while (true){
                        String s=br.readLine();
                        if(s==null)break;
                        sb.append(s);Log.d("SHOPOHOP",s);
                    }
                    if(sb.toString().contains("success")) { Log.d("SHOPOHOP","SUCCESS");
                        //remove cart
                        serverpath.al_cart.clear();
                        serverpath.CART_TOTAL=0.0;
                        serverpath.VAT=0.0;
                        serverpath.TAXES=0.0;
                        serverpath.DELIVERY_CHARGES=0.0;
                        serverpath.NET_PAYABLE_AMT=0.0;
                        serverpath.ADDRESS_ID="";
                        serverpath.PAYMENT_MODE="cod";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bt_continue_shop.setEnabled(true);
                            }
                        });

                    }
                }else {
                    Log.d("SHOPOHOP",""+rescode);
                }
            }catch (Exception e){

            }
        }
    }
}
