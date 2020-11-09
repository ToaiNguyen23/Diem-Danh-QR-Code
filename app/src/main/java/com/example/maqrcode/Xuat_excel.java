package com.example.maqrcode;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Xuat_excel extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTu5iJ5vVVDr8DowHr5axxlZVBhuCJWWnU_7PIX7Wv2TF9IDo9U8AKi6_hhrMxalr-UTSt9xTdhs-cm/pub?output=xlsx";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        webView = (WebView) findViewById(R.id.webView);
    //    webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    //    WebSettings webSettings = webView.getSettings();
     //   webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}