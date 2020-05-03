package com.example.shopohop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.TaskExecutors;

import org.w3c.dom.Text;


public class frag_action_bar extends Fragment {
    ImageView action_bar_hamburger,shopping_cart_icon;
    TextView num_items;
    public frag_action_bar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_action_bar, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        num_items=(TextView)getActivity().findViewById(R.id.num_items);
        if(serverpath.al_cart==null){
            num_items.setText("0");
        }
        else{
            num_items.setText(""+serverpath.al_cart.size());
        }
        action_bar_hamburger=(ImageView)getActivity().findViewById(R.id.action_bar_hamburger);
        action_bar_hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout dl=(DrawerLayout) getActivity().findViewById(R.id.drawer);
                dl.openDrawer(GravityCompat.START);
            }
        });

        shopping_cart_icon=(ImageView)getActivity().findViewById(R.id.shoppong_cart_icon);
        shopping_cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.activity_user_home,new shopping_cart_frag5());
                ft.commit();
            }
        });
    }


}
