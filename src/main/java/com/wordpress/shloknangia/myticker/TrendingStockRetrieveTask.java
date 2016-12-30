package com.wordpress.shloknangia.myticker;

import android.os.AsyncTask;
import android.util.Log;

import com.wordpress.shloknangia.myticker.models.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ssdn on 11/8/2016.
 */

public class TrendingStockRetrieveTask extends AsyncTask<Void, Void, String> {

    public static ArrayList<Stock> stockList;
    String json_url;

    public static final String TAG = "stockAct";

    @Override
    protected void onPreExecute() {
        stockList = new ArrayList<>();
        json_url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo" +
                ".finance.quotes%20where%20symbol%20IN%20(%22YHOO%22,%22AAPL%22,%22GOOG%22," +
                "%22AMZN%22,%22MSFT%22,%22TCS%22," +
                "%22ITC%22,%22SBI%22,%22ORN%22,%22T%22," +
                "%22INFY%22)&format=json&env=http://datatables.org/alltables.env";
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String buffer = br.readLine();
            while (buffer != null) {
                sb.append(buffer);
                buffer = br.readLine();
            }

            return sb.toString();
        } catch(Exception e){
            Log.d(TAG, "Error in doInBackground : " + e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            Log.d(TAG, s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONObject("query").getJSONObject("results").getJSONArray("quote");

            int max = jsonArray.length();
            int count=0;

            String name, symbol, last_price, percent_change;
            boolean gain=true;

            JSONObject jo;

            while(count<max) {
                jo = jsonArray.getJSONObject(count);
                name = jo.getString("Name");
                symbol = jo.getString("symbol");
                last_price = jo.getString("LastTradePriceOnly");
                percent_change = jo.getString("ChangeinPercent");

                if(percent_change.charAt(0)=='-'){
                    gain=false;
                }

                Log.d(TAG, "Name : " + name);
                Log.d(TAG, "Symbol : " + symbol);
                Log.d(TAG, "Last price : " + last_price);
                Log.d(TAG, "Percent Change : " + percent_change);
                Log.d(TAG, "Gain : " + gain);

                stockList.add(new Stock(name, symbol, last_price, percent_change, gain));

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
