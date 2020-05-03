package com.example.shopohop;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.BatchUpdateException;
import java.util.ArrayList;


public class products_frag3 extends Fragment {
ArrayList<products> products;
    TextView tv_products;
    GridView gv_products;
    products_adapter ad;

    public products_frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_frag3, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        serverpath.LAST_FRAG=new user_home_frag2();
        products=new ArrayList<>();
        tv_products=(TextView)getActivity().findViewById(R.id.tv_products);
        tv_products.setText("Products under "+serverpath.SUB_CATEGORY);
        gv_products=(GridView)getActivity().findViewById(R.id.gv_products);
        ad=new products_adapter();
        gv_products.setAdapter(ad);
        new Thread(new get_products()).start();
        gv_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serverpath.PRODUCT_ID=products.get(position).product_id;
                serverpath.LAST_FRAG= products_frag3.this;
                android.support.v4.app.FragmentManager fm=getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();Log.d("SHOOHOP","HERERE1111");
                ft.replace(R.id.activity_user_home,new single_product_frag4());
                ft.commit();
            }
        });
    }


    private class get_products implements Runnable {
        @Override
        public void run() {

            try {
                String urlpath=serverpath.SERVERPATH+"get_products?sub_category="+ URLEncoder.encode(serverpath.SUB_CATEGORY,"UTF-8"); Log.d("SHOPOHOP",urlpath);
                //String encodedUrl = URLEncoder.encode(urlpath,"UTF-8");
                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int rescode = connection.getResponseCode();
                if(rescode==HttpURLConnection.HTTP_OK){
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb=new StringBuffer();
                    while(true){
                        String s=br.readLine();
                        if(s==null){
                            break;
                        }
                        sb.append(s);
                    } Log.d("SHOPOHOP",sb.toString());
                    JSONArray jsonArray=new JSONArray(sb.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String product_id=jsonObject.getString("product_id");
                        String p_name=jsonObject.getString("p_name");
                        String p_photo=serverpath.SERVERPATH+jsonObject.getString("p_photo");
                        String p_mrp=jsonObject.getString("p_mrp");
                        String p_offer_price=jsonObject.getString("p_offer_price");

                        products.add(new products(product_id,p_name,p_photo,p_mrp,p_offer_price));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ad.notifyDataSetChanged();
                        }
                    });

                }else {
                    Log.d("SHOPOHOP","gadbad"+rescode);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class products {
        String product_id,p_name,p_photo,p_mrp,p_offer_price;

        public products(String product_id, String p_name, String p_photo, String p_mrp, String p_offer_price) {
            this.product_id = product_id;
            this.p_name = p_name;
            this.p_photo = p_photo;
            this.p_mrp = p_mrp;
            this.p_offer_price = p_offer_price;
        }
    }

   class products_adapter extends BaseAdapter{

       @Override
       public int getCount() {
           return products.size();
       }

       @Override
       public Object getItem(int position) {
           return products.get(position);
       }

       @Override
       public long getItemId(int position) {
           return 20*position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           LayoutInflater inflater=LayoutInflater.from(getActivity());
           convertView=inflater.inflate(R.layout.product_list,parent,false);
           ImageView iv=(ImageView)convertView.findViewById(R.id.iv_product_list);
           Picasso.with(getActivity()).load(products.get(position).p_photo).into(iv);
           TextView tv1=(TextView)convertView.findViewById(R.id.tv1_product_list);
           tv1.setText(products.get(position).p_name);
           TextView tv2=(TextView)convertView.findViewById(R.id.tv2_product_list);
           TextView tv3=(TextView)convertView.findViewById(R.id.tv3_product_list);
           if(products.get(position).p_offer_price.equals(products.get(position).p_mrp)){
               tv2.setText(products.get(position).p_offer_price);
           }
           else {
               tv2.setText(products.get(position).p_mrp);
               tv2.setPaintFlags(tv2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
               tv3.setText(products.get(position).p_offer_price);
           }


           return convertView;
       }
   }
}
