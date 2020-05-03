package com.example.shopohop;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class user_home_frag1 extends Fragment {
    CarouselView carouselView;
    ListView lv_categories;
    JSONArray banners,jsonArray;  //jsonArray could not be used directly as data for the listview since the incoming photo link is relative, to store url of the photo we needed an array list and an inner class categories
    ArrayList<category> categories;
    cat_adapter ad;


    public user_home_frag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home_frag1, container, false);
    }
    public void onStart(){
        super.onStart();
        serverpath.LAST_FRAG=null; // so that on back press on this fragment the control does

        new Thread(new get_carousel()).start();
        carouselView = (CarouselView) getActivity().findViewById(R.id.carouselView);
        new Thread(new get_categories()).start();
        lv_categories=(ListView)getActivity().findViewById(R.id.f1_categories);
        categories=new ArrayList<>();
        ad=new cat_adapter();
        lv_categories.setAdapter(ad);
        lv_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set global string category
                serverpath.CATEGORY=categories.get(position).c_name;
                serverpath.LAST_FRAG= user_home_frag1.this;
                android.support.v4.app.FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.activity_user_home,new user_home_frag2());
                ft.commit();
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
            try {
                JSONObject jo = (JSONObject)banners.get(position);

            Picasso.with(getActivity()).load(serverpath.SERVERPATH+jo.getString("banner_photo")).into(imageView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

class get_carousel implements Runnable{
    @Override
    public void run(){
    final String urlpath = serverpath.SERVERPATH+"get_banners";
       try {
           URL url = new URL(urlpath);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("POST");
           int rescode = connection.getResponseCode();
           if(rescode==HttpURLConnection.HTTP_OK){
               BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
               StringBuffer sb = new StringBuffer();
               while(true){
                   String s = br.readLine();
                   if(s==null) break;
                   sb.append(s);
               }

                banners = new JSONArray(sb.toString()); //jsonArray gets memory here for the first time .... use it after this only else it will give null pointer exception

               Log.d("SHOPOHOP",banners.length()+"");
               carouselView.setImageListener(imageListener);
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       carouselView.setPageCount(banners.length()); //carousel is created bcoz of this line only therefore it makes changes to ui hence in ruinonuithread

                   }
               });



           }else if(rescode==HttpURLConnection.HTTP_NOT_FOUND){
               Log.d("SHOPOHOP","404 NOT FOUND");
           }
           else{
               Log.d("SHOPOHOP","response code is "+rescode);
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }

    }
}
    class get_categories implements Runnable{
        @Override
        public void run(){
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
                        categories.add(new category(photo_url,c_name));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ad.notifyDataSetChanged();
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
    class category {
        String photo,c_name;

        public category(String photo,  String c_name) {
            this.photo = photo;
            this.c_name = c_name;
        }
    }
    class cat_adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return categories.size();
        }

        @Override
        public Object getItem(int position) {
            return categories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position*10;
        }

        @Override
        public View getView(int position, View singleRow, ViewGroup parent) {

            LayoutInflater inflater=LayoutInflater.from(getActivity());
            singleRow=inflater.inflate(R.layout.f1_categories,parent,false);
            ImageView iv=(ImageView)singleRow.findViewById(R.id.iv_categories);
            TextView tv=(TextView)singleRow.findViewById(R.id.tv_categories);
            Picasso.with(getActivity()).load(categories.get(position).photo).into(iv);
            tv.setText(categories.get(position).c_name);
            return singleRow;
        }
    }


}
