package com.example.shopohop;

//COPY TO IMPORTS

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class demo extends AppCompatActivity {
    int res[] ={R.drawable.brezza,R.drawable.creta,R.drawable.dzire,R.drawable.swift,R.drawable.hondacity,R.drawable.mahindra_xuv,R.drawable.verna};
    ListView lv;
    ArrayList<category> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);     //***CHANGE LAYOUT FILE
        LinearLayout ll = (LinearLayout)findViewById(R.id.activity_demo);   //****CHANGE ID OF LL

        for(int i=0;i<res.length;i++){
            //CREATING IMAGEVIEW PROGRAMMATICALLT IN LINEARLAYOUT
            ImageView iv = new ImageView(this);
            iv.setImageResource(res[i]);
            iv.setLayoutParams(new android.view.ViewGroup.LayoutParams(400,400));
            iv.setClickable(true);
            ll.addView(iv);
            final int j=i;
            // ADDING ONCLICK LISTENER
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // CREATING CUSTOM DIALOG
                    final Dialog dialog = new Dialog(demo.this);    //***CHANGE ACTIVITY NAME --?? NOT WORKING WITH GETAPPLICATION CONTEXT
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(res[j]);
                    dialog.setContentView(imageView);
                    dialog.show();
                }
            });
        }
        lv=(ListView) findViewById(R.id.lv);
        //String array[]={"cat1","cat2","cat3"};
        al = new ArrayList<>();
        al.add(new category("acascs","cat1"));
        al.add(new category("acascs","cat1"));
        al.add(new category("acascs","cat1"));
       // ArrayAdapter<category> ad = new ArrayAdapter<category>(this,android.R.layout.simple_list_item_1,al);
        cat_adapter ad=new cat_adapter();
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.activity_demo,new user_home_frag1());
                ft.commit();
            }
        });
    }
    class category{
        String photo,c_name;

        public category(String c_name, String photo) {
            this.c_name = c_name;
            this.photo = photo;
        }
    }
    class cat_adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return al.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View singleRow, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(demo.this);
            singleRow=inflater.inflate(R.layout.f1_categories,parent,false);
            //ImageView iv=(ImageView)singleRow.findViewById(R.id.iv_categories);
            TextView tv=(TextView)singleRow.findViewById(R.id.tv_categories);
            //Picasso.with(getApplicationContext()            )
            tv.setText(al.get(position).c_name);
            return singleRow;
        }
    }
}
