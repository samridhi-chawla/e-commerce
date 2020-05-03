package com.example.shopohop;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class my_orders extends AppCompatActivity {
WebView wv_my_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        wv_my_orders=(WebView)findViewById(R.id.wv_my_orders);
        SharedPreferences sp=getSharedPreferences("mypref",MODE_PRIVATE);
        String email=sp.getString("user_email","");
        wv_my_orders.setWebViewClient(new myWebViewClient());
        wv_my_orders.loadUrl(serverpath.SERVERPATH+"user/mobile_my_orders.jsp?email="+email);
        WebSettings webSettings=wv_my_orders.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    class myWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }
    }
}
