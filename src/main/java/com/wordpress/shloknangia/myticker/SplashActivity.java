package com.wordpress.shloknangia.myticker;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wordpress.shloknangia.myticker.models.Stock;
import java.util.ArrayList;

public class SplashActivity extends Activity {

    public static final String TAG = "myAct";

    ProgressBar progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progBar = (ProgressBar) findViewById(R.id.progBar);

        if(checkInternet()) {
            new TrendingStockRetrieveTask().execute();
        } else {
            Toast.makeText(SplashActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                        startActivity(i);
                        finish();
                    }
                },4000
        );
    }

    private boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
