package com.wordpress.shloknangia.myticker;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SHLOK on 06-10-2016.
 */
public class StockRetriveTask extends AsyncTask<String, Void, String>  {

    private static final String TAG = "StockRetrieveTask";

    @Override
    protected String doInBackground(String... urls) {

        Log.i(TAG, "doInBackground");

        try {
            HttpURLConnection connection = (HttpURLConnection) (new URL(urls[0])).openConnection();
            connection.connect();

            return connectionToString(connection.getInputStream());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    private String connectionToString(InputStream is) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String buffer = br.readLine();
            while (buffer != null) {
                sb.append(buffer);
                buffer = br.readLine();
            }
            return sb.toString();
        } catch(Exception e) {
        }
        return null;
    }

}
