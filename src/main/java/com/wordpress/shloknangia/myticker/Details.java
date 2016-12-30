package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    String message1,message2,message3,message,message4,message5,message6,message7,message8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("stockname");
        message1 = bundle.getString("noofstocks");
        message2 = bundle.getString("costrate");
        message3 = bundle.getString("stocksbought");
        message4 = bundle.getString("costprice");
        message5 = bundle.getString("sellrate");
        message6 = bundle.getString("stockssold");
        message7 = bundle.getString("sellprice");
        message8 = bundle.getString("payoff");


        TextView txtView = (TextView) findViewById(R.id.tv_symbol_d);
        TextView txtView1 = (TextView)findViewById(R.id.tv_noofstocks_d);
        TextView txtView2 = (TextView) findViewById(R.id.tv_costrate_d);
        TextView txtView3 = (TextView) findViewById(R.id.tv_stocksbought_d);
        TextView txtView4 = (TextView)findViewById(R.id.tv_costprice_d);
        TextView txtView5 = (TextView) findViewById(R.id.tv_sellrate_d);
        TextView txtView6 = (TextView)findViewById(R.id.tv_stockssold_d);
        TextView txtView7 = (TextView) findViewById(R.id.tv_sellprice_d);
        TextView txtView8 = (TextView)findViewById(R.id.tv_payoff_d);

        txtView.setText(message);
        txtView1.setText(message1);
        txtView2.setText(message2);
        txtView3.setText(message3);
        txtView4.setText(message4);
        txtView5.setText(message5);
        txtView6.setText(message6);
        txtView7.setText(message7);
        txtView8.setText(message8);

    }

    public void modify (View view){

        Intent n = new Intent(Details.this,ModifyOrSell.class);

        n.putExtra("stockname", message);
        n.putExtra("noofstocks", message1);
        n.putExtra("costrate",message2);
        n.putExtra("stocksbought", message3);
        n.putExtra("costprice",message4);
        n.putExtra("sellrate", message5);
        n.putExtra("stockssold", message6);
        n.putExtra("sellprice",message7);
        n.putExtra("payoff", message8);

        startActivity(n);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
