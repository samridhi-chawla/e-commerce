package com.example.shopohop;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class place_order_frag6 extends Fragment {
    TextView order_total, order_vat, order_taxes, order_delivery_charges, order_net_payable_amt, user_name, user_email, user_phone, error_add_empty;
    RadioButton payment_cod, payment_digital;
    JSONArray address_array;
    Button bt_add_addres, bt_confirm_order;

    public place_order_frag6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_order_frag6, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        order_total = (TextView) getActivity().findViewById(R.id.order_total);
        order_vat = (TextView) getActivity().findViewById(R.id.order_vat);
        order_taxes = (TextView) getActivity().findViewById(R.id.order_taxes);
        order_delivery_charges = (TextView) getActivity().findViewById(R.id.order_delivery_charges);
        order_net_payable_amt = (TextView) getActivity().findViewById(R.id.order_net_payable_amt);
        error_add_empty = (TextView) getActivity().findViewById(R.id.error_add_empty);
        serverpath.VAT = serverpath.CART_TOTAL * 0.05; //15% of total
        serverpath.TAXES = serverpath.CART_TOTAL * 0.12;
        serverpath.DELIVERY_CHARGES = 50.0;
        serverpath.NET_PAYABLE_AMT = serverpath.CART_TOTAL + serverpath.VAT + serverpath.TAXES + serverpath.DELIVERY_CHARGES;

        order_total.setText(serverpath.CART_TOTAL + "");
        order_vat.setText(serverpath.VAT + "");
        order_taxes.setText(serverpath.TAXES + "");
        order_delivery_charges.setText(serverpath.DELIVERY_CHARGES + "");
        order_net_payable_amt.setText(serverpath.NET_PAYABLE_AMT + "");

        user_email = (TextView) getActivity().findViewById(R.id.user_email);
        user_name = (TextView) getActivity().findViewById(R.id.user_name);
        user_phone = (TextView) getActivity().findViewById(R.id.user_phone);
        new Thread(new get_user_details()).start();
        new Thread(new get_user_addresses()).start();

        bt_add_addres = (Button) getActivity().findViewById(R.id.bt_add_address);
        bt_add_addres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), add_new_address.class);
                startActivity(in);

            }
        });
        payment_cod = (RadioButton) getActivity().findViewById(R.id.payment_cod);
        payment_digital = (RadioButton) getActivity().findViewById(R.id.payment_digital);
        payment_cod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (payment_cod.isChecked()) {
                    serverpath.PAYMENT_MODE = "cod";
                } else {
                    serverpath.PAYMENT_MODE = "digital";
                }
            }
        });
        bt_confirm_order = (Button) getActivity().findViewById(R.id.bt_confirm_order);
        bt_confirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serverpath.ADDRESS_ID.isEmpty()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            error_add_empty.setText("PLease select a delivery address");
                        }
                    });
                } else {
                    if (serverpath.PAYMENT_MODE.equals("cod")) {
                        Intent in = new Intent(getActivity(), final_page.class);
                        getActivity().finish();
                        startActivity(in);
                    } else if (serverpath.PAYMENT_MODE.equals("digital")) {
                        Intent in = new Intent(getActivity(), payment.class);
                        startActivity(in);
                    }
                }
            }
        });



    }

    class get_user_details implements Runnable {

        @Override
        public void run() {
            try {Log.d("HELLO","INSIDE THREAD");
                SharedPreferences sp = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
                String sharedpref_email = sp.getString("user_email", "");
                String urlpath = serverpath.SERVERPATH + "get_user_details?email=" + sharedpref_email;
                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int rescode = connection.getResponseCode();
                if (rescode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String s = br.readLine();
                        if (s == null) break;
                        sb.append(s);
                    }
                    Log.d("SHOPOHOP", sb.toString());
                    JSONObject jsonObject = new JSONObject(sb.toString());
                    serverpath.USER_NAME = jsonObject.getString("name");
                    serverpath.USER_PHONE = jsonObject.getString("phone_no");
                    serverpath.USER_EMAIL = sharedpref_email;
                    //SET THESE VALUES TO TEXTVIEWS
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            user_name.setText(serverpath.USER_NAME);
                            user_email.setText(serverpath.USER_EMAIL);
                            user_phone.setText(serverpath.USER_PHONE);
                        }
                    });

                } else {
                    Log.d("SHOPOHOP", "" + rescode);
                }
            } catch (Exception e) {

            }
        }
    }

    class get_user_addresses implements Runnable {

        @Override
        public void run() {
            try {
                Log.d("Hello","calling thread ");
                SharedPreferences sp = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
                String sharedpref_email = sp.getString("user_email", "");
                String urlpath = serverpath.SERVERPATH + "get_user_addresses?email=" + sharedpref_email;
                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int rescode = connection.getResponseCode();
                if (rescode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String s = br.readLine();
                        if (s == null) break;
                        sb.append(s);
                    }
                    Log.d("SHOPOHOP", sb.toString());
                    address_array = new JSONArray(sb.toString());
                    final RadioGroup address_radio_group = (RadioGroup) getActivity().findViewById(R.id.address_radio__group);
                    //LinearLayout ll=(LinearLayout)getActivity().findViewById(R.id.user_addresses);
                    for (int i = 0; i < address_array.length(); i++) {
                        final JSONObject jsonObject = address_array.getJSONObject(i);
                        String address = jsonObject.getString("address") + ", " + jsonObject.getString("city") + ", " + jsonObject.getString("state") + ", " + jsonObject.getString("pincode");
                        final RadioButton rb = new RadioButton(getActivity());
                        rb.setId(i * 19);
                        rb.setText(address);
                        if (i == (address_array.length() - 1)) {
                            rb.setChecked(true);
                            Log.d("SHOPOHOP", "address " + i + " is checked");
                            serverpath.ADDRESS_ID = jsonObject.getString("add_id");
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                address_radio_group.addView(rb);
                            }
                        });

                        Log.d("MESSAGE",i+" value");

                        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (rb.isChecked()) {
                                    try {
                                        serverpath.ADDRESS_ID = jsonObject.getString("add_id");
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                error_add_empty.setText("");
                                            }
                                        });
                                        Log.d("SHOPOHOP", serverpath.ADDRESS_ID);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }

                } else {
                    Log.d("SHOPOHOP", "" + rescode);
                }
            } catch (Exception e) {

            }
        }
    }

}
