package com.example.shopohop;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class user_home_frag2 extends Fragment {

    String category;
    TextView f2_heading;
    ListView f2_lv_subcategories;
    subcategories_adapter ad;
    JSONArray jsonArray;
    ArrayList<sub_category> sub_categories;
    public user_home_frag2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home_frag2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        serverpath.LAST_FRAG=new user_home_frag1();
        category= serverpath.CATEGORY;  //get from global class variable
        f2_heading=(TextView) getActivity().findViewById(R.id.f2_heading);
        f2_heading.setText(category);
        f2_lv_subcategories=(ListView)getActivity().findViewById(R.id.f2_lv_subcategories);
        sub_categories=new ArrayList<>();
        ad=new subcategories_adapter();
        f2_lv_subcategories.setAdapter(ad);
        f2_lv_subcategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            serverpath.SUB_CATEGORY=sub_categories.get(position).sub_c_name;
                serverpath.LAST_FRAG=user_home_frag2.this;
                android.support.v4.app.FragmentManager fm=getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();Log.d("SHOOHOP","HERERE1111");
                ft.replace(R.id.activity_user_home,new products_frag3());
                ft.commit();

            }
        });
        new Thread(new get_subcategories()).start();
    }

    class get_subcategories implements Runnable{
        @Override
        public void run() {
            try {
                String urlpath = serverpath.SERVERPATH + "get_subcategories?category=" + URLEncoder.encode(category,"UTF-8");
                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //connection.setRequestMethod("POST");
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb= new StringBuffer();
                    while(true){
                        String s = br.readLine();
                        if(s==null) break;
                        sb.append(s);
                    }
                    jsonArray=new JSONArray(sb.toString());
                    ///////////////////////////////////
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String sub_c_name=jsonObject.getString("sub_c_name"); Log.d("SHOPOHOP",sub_c_name);
                        String photo=serverpath.SERVERPATH+jsonObject.getString("photo");
                        String description=jsonObject.getString("description");
                        sub_categories.add(new sub_category(sub_c_name,photo,description));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ad.notifyDataSetChanged();
                        }
                    });
                }else if(rescode==HttpURLConnection.HTTP_NOT_FOUND){
                    Log.d("SHOPOHOP","404 NOT FOUND "+rescode);
                }else {
                    Log.d("SHOPOHOP", ""+rescode);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //inner class sub category
    class sub_category {
        String sub_c_name,photo,description;

        public sub_category(String sub_c_name, String photo, String description) {
            this.sub_c_name = sub_c_name;
            this.photo = photo;
            this.description = description;
        }
    }
    class subcategories_adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return sub_categories.size();
        }

        @Override
        public Object getItem(int position) {
            return sub_categories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position*20;
        }

        @Override
        public View getView(int position, View singleRow, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            singleRow=inflater.inflate(R.layout.f1_categories,parent,false);
            ImageView iv=(ImageView)singleRow.findViewById(R.id.iv_categories);
            TextView tv=(TextView)singleRow.findViewById(R.id.tv_categories);
            tv.setText(sub_categories.get(position).sub_c_name);
            Picasso.with(getActivity()).load(sub_categories.get(position).photo).into(iv);
            return singleRow;
        }
    }
}
