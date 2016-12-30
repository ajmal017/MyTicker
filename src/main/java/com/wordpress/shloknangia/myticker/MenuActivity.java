package com.wordpress.shloknangia.myticker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.shloknangia.myticker.models.Stock;
import java.io.File;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView list;
    ListAdapter adapter;
    public static final String TAG = "myAct";

    ArrayList<Stock> stocksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        stocksList = TrendingStockRetrieveTask.stockList;

        Log.d(TAG, "MenuActivity : ");
        for(int i=0;i<stocksList.size();i++){
            Log.d(TAG, stocksList.get(i).getName());
        }

        list = (ListView)findViewById(R.id.trendingStocksList);
        adapter = new ListAdapter(MenuActivity.this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MenuActivity.this, CheckPrice.class);
                intent.putExtra("symbol", stocksList.get(i).getSymbol());
                startActivity(intent);
            }
        });
    }



    public class ListAdapter extends BaseAdapter {

        LayoutInflater li;

        public ListAdapter(Context context) {
            li = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return stocksList.size();
        }

        @Override
        public Stock getItem(int i) {
            return stocksList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView = li.inflate(R.layout.stock_list_item, null, true);
            Stock stock = getItem(i);
            ((TextView)convertView.findViewById(R.id.stockName)).setText(stock.getName());
            ((TextView)convertView.findViewById(R.id.stockSymbol)).setText(stock.getSymbol());
            ((TextView)convertView.findViewById(R.id.currPrice)).setText(stock.getPrice());
            ((TextView)convertView.findViewById(R.id.percentChange)).setText(stock.getChange());
//            if(stock.isGain()){
            String a = stock.getChange();
                if(a.charAt(0) == '+'){
                ((ImageView)convertView.findViewById(R.id.imageView)).setBackground(getResources().getDrawable(R.mipmap.upward));
            }
            else{
                ((ImageView)convertView.findViewById(R.id.imageView)).setBackground(getResources().getDrawable(R.mipmap.downward));
            }


            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MenuActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.credits){
            Toast.makeText( this,"Minor Project\nRajat Goyal \tMC/059\nShlok Nangia \tMC/078\nRohit Kumar \tMC/066",Toast.LENGTH_LONG).show();
        } else if(id == R.id.refresh) {
            new TrendingStockRetrieveTask().execute();
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_portfolio) {
//            finish();
//            startActivity(new Intent(MenuActivity.this, MenuActivity.class));

        } else if (id == R.id.nav_investment) {
            startActivity(new Intent(MenuActivity.this, MyStocks.class));

        } else if (id == R.id.nav_stock_value) {
            startActivity(new Intent(MenuActivity.this, CheckPrice.class));

        } else if (id == R.id.nav_help) {
            startActivity(new Intent(MenuActivity.this, HelpActivity.class));

        } else if (id == R.id.nav_share) {

            try{
                ArrayList<Uri> uris = new ArrayList<Uri>();
                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                sendIntent.setType("application/vnd.android.package-archive");
                uris.add(Uri.fromFile(new File(getApplicationInfo().publicSourceDir)));
                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                startActivity(Intent.createChooser(sendIntent, null));


            }catch(Exception e){

                ArrayList<Uri> uris = new ArrayList<Uri>();
                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                sendIntent.setType("application/zip");
                uris.add(Uri.fromFile(new File(getApplicationInfo().publicSourceDir)));
                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                startActivity(Intent.createChooser(sendIntent, null));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
