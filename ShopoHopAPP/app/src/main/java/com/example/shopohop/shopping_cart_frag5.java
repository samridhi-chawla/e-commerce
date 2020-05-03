package com.example.shopohop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class shopping_cart_frag5 extends Fragment {
    Button bt_checkout;
    ListView cart_list;
    cart_list_adapter cart_list_adapter;
    TextView shopping_cart_total;

    public shopping_cart_frag5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart_frag5, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        bt_checkout=(Button)getActivity().findViewById(R.id.bt_checkout);
        if(serverpath.al_cart.size()>0){
            bt_checkout.setEnabled(true);
        }
        bt_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           // serverpath.LAST_FRAG=shopping_cart_frag5.this;
                Intent in = new Intent(getContext(),checkout.class);
                startActivity(in);

            }
        });

        cart_list=(ListView)getActivity().findViewById(R.id.cart_list);
        cart_list_adapter=new cart_list_adapter();
        cart_list.setAdapter(cart_list_adapter);
        shopping_cart_total=(TextView)getActivity().findViewById(R.id.shoping_cart_total);


    }
    class cart_list_adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return serverpath.al_cart.size();
        }

        @Override
        public Object getItem(int position) {
            return serverpath.al_cart.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position*17;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            convertView=inflater.inflate(R.layout.cart_list,parent,false);
            ImageView iv =(ImageView)convertView.findViewById(R.id.cart_item_photo);
            Picasso.with(getActivity()).load(serverpath.SERVERPATH+serverpath.al_cart.get(position).p_photo).into(iv);
            //Log.d("SHOPOHOP",serverpath.al_cart.get(position).p_name);
            TextView tv_name=(TextView)convertView.findViewById(R.id.cart_item_name);
            tv_name.setText(serverpath.al_cart.get(position).p_name);
            //Log.d("SHOPOHOP",serverpath.al_cart.get(position).p_subcategory);
            TextView tv_subcat=(TextView)convertView.findViewById(R.id.cart_item_sub_category);
            tv_subcat.setText(serverpath.al_cart.get(position).p_subcategory);
            //Log.d("SHOPOHOP",serverpath.al_cart.get(position).p_offer_price);
            TextView tv_mrp=(TextView)convertView.findViewById(R.id.cart_item_mrp);
            tv_mrp.setText(serverpath.al_cart.get(position).p_offer_price);
            //delete button logic
            Button cart_item_del=(Button)convertView.findViewById(R.id.cart_item_del);
            cart_item_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serverpath.al_cart.remove(position);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            serverpath.CART_TOTAL=0.0;//just before reset the net ayable is set to 0 so that correct amt is calculated
                            if(serverpath.al_cart.size()==0){ //textview is not updated if all the items are deleted coz it does not enter into this adapter function therefore manually set cart total to 0
                                shopping_cart_total.setText("Total Amount is "+serverpath.CART_TOTAL);
                            }
                            cart_list_adapter.notifyDataSetChanged(); //refresh the cart list
                            TextView action_bar_num_items = (TextView) getActivity().findViewById(R.id.num_items);
                            action_bar_num_items.setText("" + serverpath.al_cart.size());
                        }
                    });
                }
            });
            //add subtract qty text logic
            TextView cart_item_qty=(TextView) convertView.findViewById(R.id.cart_item_qty);
            Log.d("SHOPOHOP",serverpath.al_cart.get(position).qty+"");
            cart_item_qty.setText(""+serverpath.al_cart.get(position).qty);
            ImageView bt_add_qty=(ImageView)convertView.findViewById(R.id.bt_add_qty);
            bt_add_qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serverpath.al_cart.get(position).qty++;
                    serverpath.CART_TOTAL=0.0;//just before reset the net payable is set to 0 so that correct amt is calculated
                    cart_list_adapter.notifyDataSetChanged();
                }
            });
            ImageView bt_minus_qty=(ImageView)convertView.findViewById(R.id.bt_minus_qty);
            bt_minus_qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(serverpath.al_cart.get(position).qty>1) {
                        serverpath.al_cart.get(position).qty--;
                    }
                    serverpath.CART_TOTAL=0.0; //just before reset the net payable is set to 0 so that correct amt is calculated
                    cart_list_adapter.notifyDataSetChanged();
                }
            });
            TextView cart_item_tot=(TextView)convertView.findViewById(R.id.cart_item_tot);
            try {
                double p_offer_price = Double.parseDouble(serverpath.al_cart.get(position).p_offer_price);
                double tot = serverpath.al_cart.get(position).qty * p_offer_price;
                cart_item_tot.setText("" + tot);
                serverpath.CART_TOTAL=serverpath.CART_TOTAL+tot;
                shopping_cart_total.setText("Total Amount is "+serverpath.CART_TOTAL);
            }catch (Exception e){
                e.printStackTrace();
            }
            return convertView;
        }
    }

}
