package com.example.shopohop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.URLEncoder;
import java.util.ArrayList;

public class single_product_frag4 extends Fragment {
    CarouselView cv_product;
    JSONArray photos;
    JSONObject product;
    TextView p_name,p_subcat,p_cat,p_descrip,p_mrp,p_offer_price;
    Button bt_add_to_cart;
    public single_product_frag4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_product_frag4, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        serverpath.LAST_FRAG=new products_frag3();
        cv_product=(CarouselView) getActivity().findViewById(R.id.cv_product);
        p_name=(TextView)getActivity().findViewById(R.id.p_name);
        p_subcat=(TextView)getActivity().findViewById(R.id.p_subcat);
        p_cat=(TextView)getActivity().findViewById(R.id.p_cat);
        p_descrip=(TextView)getActivity().findViewById(R.id.p_descrip);
        p_mrp=(TextView)getActivity().findViewById(R.id.p_mrp);
        p_offer_price=(TextView)getActivity().findViewById(R.id.p_offer_price);
        bt_add_to_cart=(Button)getActivity().findViewById(R.id.bt_add_to_cart);
        bt_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {//check for unique item in the cart
                        int i;
                        for(i=0;i<serverpath.al_cart.size();i++){
                            if(serverpath.al_cart.get(i).p_id.equals(product.getString("product_id"))){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"This item is already added to your cart",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            }
                        }
                        if(i==serverpath.al_cart.size()) { //add item to cart
                            serverpath.al_cart.add(new cart_item(product.getString("product_id"), product.getString("p_name"),product.getString("sub_category"),product.getString("p_descrip"), 1, product.getString("p_offer_price"), product.getString("p_photo")));
                            TextView tv = (TextView) getActivity().findViewById(R.id.num_items);
                            tv.setText("" + serverpath.al_cart.size());
                            bt_add_to_cart.setEnabled(false);
                        }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        new Thread(new get_product_gallery()).start();
        new Thread(new get_product_details()).start();

    }
    class get_product_gallery implements Runnable{

        @Override
        public void run() {
            try{
                String urlpath=serverpath.SERVERPATH+"get_product_gallery?p_id="+serverpath.PRODUCT_ID;
                URL url=new URL(urlpath);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while(true){
                        String s = br.readLine();
                        if(s==null) break;
                        sb.append(s);
                    }

                    photos = new JSONArray(sb.toString()); //jsonArray gets memory here for the first time .... use it after this only else it will give null pointer exception
                    cv_product.setImageListener(imageListener);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cv_product.setPageCount(photos.length());
                        }
                    });

                }else
                {
                    Log.d("SHOPOHOP",""+rescode);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    ImageListener imageListener=new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            try{
                JSONObject jsonObject=photos.getJSONObject(position);
                Picasso.with(getActivity()).load(serverpath.SERVERPATH+jsonObject.getString("path")).into(imageView);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
    class get_product_details implements Runnable{

        @Override
        public void run() {
            try {
                String urlpath = serverpath.SERVERPATH + "get_single_product?p_id=" + URLEncoder.encode(serverpath.PRODUCT_ID, "Utf-8");
                URL url=new URL(urlpath); Log.d("SHOPOHOP",urlpath);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                int rescode=connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){ Log.d("SHOPOHOP","RESCOF=DE 200");
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb=new StringBuffer();
                    while (true) {
                        String s = br.readLine();
                        if (s == null) {
                            break;
                        }
                        sb.append(s);
                    }
                    Log.d("SHOPOHOP",sb.toString());
                    product=new JSONObject(sb.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                p_name.setText(product.getString("p_name"));
                                p_subcat.setText(product.getString("sub_category"));
                                p_cat.setText(product.getString("category"));
                                p_descrip.setText(product.getString("p_descrip"));
                                p_mrp.setText(product.getString("p_mrp"));
                                p_offer_price.setText(product.getString("p_offer_price"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }else {
                    Log.d("SHOPOHOP",""+rescode);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
