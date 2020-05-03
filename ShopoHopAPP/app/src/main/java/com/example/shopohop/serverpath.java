package com.example.shopohop;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class serverpath {
    public static String SERVERPATH = "http://192.168.43.231:8084/myproject1/";
    public  static String USER_EMAIL="";
    public  static String USER_NAME="";
    public  static String USER_PHONE="";
    public static String CATEGORY="";
    public static String SUB_CATEGORY="";
    public static String PRODUCT_ID="";
    public static String ADDRESS_ID="";
    public static ArrayList<cart_item> al_cart=new ArrayList<>();
    public static Fragment LAST_FRAG;

    public static double CART_TOTAL=0.0;
    public static double VAT=0.0;
    public static double TAXES=0.0;
    public static double DELIVERY_CHARGES=0.0;
    public static double NET_PAYABLE_AMT=0.0;
    public static String PAYMENT_MODE="cod";

}
//in shared pref mypref user_email is stored