package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleSearchActivity extends AppCompatActivity {



    private static final String SEARCH_URL = "https://www.google.co.in/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");

        String url = SEARCH_URL + keyword;

        WebView webView = (WebView)findViewById(R.id.activity_google_search);

        WebSettings webSettings = webView.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        // to increase webview performance
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl(url);
    }
}

class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}

