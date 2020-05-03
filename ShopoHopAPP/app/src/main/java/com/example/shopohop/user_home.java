package com.example.shopohop;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class user_home extends AppCompatActivity {

    String arr[];
    ListView lv_nav;
    JSONArray jsonArray;
    //for navigation view
    LinkedList<String> nav_list_items ;
    ArrayAdapter<String> nav_adapter;
    int[] sampleImages ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.activity_user_home,new user_home_frag1());
        ft.commit();

        new Thread(new get_nav_items()).start();;
        //navigation view list view is set here
        lv_nav=(ListView)findViewById(R.id.lv_nav);
        nav_list_items=new LinkedList<>();
        nav_list_items.addFirst("Home");
        final SharedPreferences sp=getSharedPreferences("mypref",MODE_PRIVATE);
        final String user_email=sp.getString("user_email","");
        if(user_email.isEmpty()){
            nav_list_items.add("Login");Log.d("SHOPOHOP","inside emptya"+user_email);
        }else {
            nav_list_items.add("Logout");
            nav_list_items.add("My Orders");
        }
        nav_adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nav_list_items);
        lv_nav.setAdapter(nav_adapter);
        lv_nav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrawerLayout dl=(DrawerLayout)findViewById(R.id.drawer);
                if(position==0){
                    dl.closeDrawer(GravityCompat.START);
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.activity_user_home, new user_home_frag1());
                    ft.commit();
                }
                else if(position==1){
                    if(user_email.isEmpty()){
                        Intent in = new Intent(getApplicationContext(),user_login.class);
                        startActivity(in);
                    }else{
                        SharedPreferences.Editor editor=sp.edit();
                        editor.remove("user_email");
                        editor.apply();
                        finish();
                        startActivity(getIntent());
                    }
                }
                else if(nav_list_items.get(position).equals("My Orders")){ //goti my orders
                        Intent in=new Intent(getApplicationContext(),my_orders.class);
                        startActivity(in);

                }
                else {
                    dl.closeDrawer(GravityCompat.START);
                    serverpath.CATEGORY = nav_list_items.get(position);
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.activity_user_home, new user_home_frag2());
                    ft.commit();
                }
            }
        });


    }

    class get_nav_items implements Runnable{

        @Override
        public void run() {
            try {
                String urlpath = serverpath.SERVERPATH + "get_categories";
                URL url = new URL(urlpath);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while (true){
                        String s =br.readLine();
                        if(s== null){
                            break;
                        }
                        sb.append(s);
                    }

                    jsonArray= new JSONArray(sb.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String photo_url=serverpath.SERVERPATH+jsonObject.getString("photo");
                        String c_name=jsonObject.getString("c_name");
                        nav_list_items.add(c_name);
                    }
                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nav_adapter.notifyDataSetChanged();
                        }
                    });

                    Log.d("SHOPOHOP","PAHIUNCHA");
                }else if(rescode==HttpURLConnection.HTTP_NOT_FOUND){
                    Log.d("SHOPOHOP","404 NOT FOUND "+rescode);
                }else {
                    Log.d("SHOPOHOP"," "+rescode);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if(serverpath.LAST_FRAG==null){
            super.onBackPressed();
        }
        else {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.activity_user_home, serverpath.LAST_FRAG);
            ft.commit();
        }
    }

}
