package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MyStocks extends AppCompatActivity {
    DatabaseAdapter databaseadapter;
    public static final String TAG = "myAct";

    ListView mylist;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stocks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseadapter = new DatabaseAdapter(this);
        Cursor cursor = databaseadapter.viewall();
        Log.d(TAG, databaseadapter.retcolstockname());
        String fieldnames[] = new String[]{databaseadapter.retcolstockname(),databaseadapter.retcolnoofstocks()};
        int[] toids = new int[]{R.id.custom_text_view, R.id.noOfStocks};

        simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.custom,cursor,fieldnames,toids,0);
//        simpleCursorAdapter = new SimpleCursorAdapter()
        mylist = (ListView)findViewById(R.id.viewlist);
        mylist.setAdapter(simpleCursorAdapter);

        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String data = String.valueOf(adapterView.getItemIdAtPosition(i));
                        Intent intent = new Intent(MyStocks.this, Details.class);
                        String[] args = databaseadapter.setvalue(data);
                        String message0 = args[0];
                        String message1 = args[1];
                        String message2 = args[2];
                        String message3 = args[3];
                        String message4 = args[4];
                        String message5 = args[5];
                        String message6 = args[6];
                        String message7 = args[7];
                        String message8 = args[8];

                        intent.putExtra("stockname", message0);
                        intent.putExtra("noofstocks", message1);
                        intent.putExtra("costrate",message2);
                        intent.putExtra("stocksbought", message3);
                        intent.putExtra("costprice",message4);
                        intent.putExtra("sellrate", message5);
                        intent.putExtra("stockssold", message6);
                        intent.putExtra("sellprice",message7);
                        intent.putExtra("payoff", message8);

                        startActivity(intent);
                    }
                }
        );

        mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = String.valueOf(adapterView.getItemIdAtPosition(i));
                String[] args = databaseadapter.setvalue(data);
                String name = args[0];
                databaseadapter.delrow(name);

                Toast.makeText(MyStocks.this, "Deleted this stock", Toast.LENGTH_SHORT).show();
                finish();
//                update();
//                final String stockprice = new MainActivity().findPrice(name);
//                Log.d(TAG, "Stockprice : " + stockprice);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "Price : " + stockprice, Toast.LENGTH_SHORT).show();
//                    }
//                },2000);
////                Toast.makeText(getApplicationContext(), "Price : " + stockprice, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

//    public void update() {
//        simpleCursorAdapter.notifyDataSetChanged();
//        mylist.setAdapter(simpleCursorAdapter);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
