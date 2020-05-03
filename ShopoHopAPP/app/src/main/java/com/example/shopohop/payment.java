package com.example.shopohop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class payment extends AppCompatActivity {
WebView payment_web_view;
    Button payment_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment_web_view=(WebView)findViewById(R.id.payment_web_view);
        payment_web_view.loadUrl(serverpath.SERVERPATH+"user/payment_from_mobile.jsp?net_amt_payable="+serverpath.NET_PAYABLE_AMT);
        payment_web_view.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings=payment_web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        payment_done=(Button)findViewById(R.id.payment_done);
        payment_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),final_page.class);
                finish();
                startActivity(in);
            }
        });
    }
    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
