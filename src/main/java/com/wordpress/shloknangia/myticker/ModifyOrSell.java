package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyOrSell extends AppCompatActivity {

    String message1,message2,message3,message,message4,message5,message6,message7,message8,s1,s2,c1,c2,p;
    EditText txtView2,txtView3,txtView5,txtView6,txtView7;
    TextView txtView,txtView1,txtView4,txtView8;

    DatabaseAdapter databaseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_or_sell);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adding comment for snackbar
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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


         txtView = (TextView) findViewById(R.id.tv_symbol_m);
         txtView1 = (TextView)findViewById(R.id.tv_noofstocks_m);
         txtView2 = (EditText) findViewById(R.id.tv_costrate_m);
         txtView3 = (EditText) findViewById(R.id.tv_stocksbought_m);
         txtView4 = (TextView)findViewById(R.id.tv_costprice_m);
         txtView5 = (EditText) findViewById(R.id.tv_sellrate_m);
         txtView6 = (EditText)findViewById(R.id.tv_stockssold_m);
         txtView7 = (EditText) findViewById(R.id.tv_sellprice_m);
         txtView8 = (TextView)findViewById(R.id.tv_payoff_m);

        txtView.setText(message);
        txtView1.setText(message1);
        txtView2.setText(message2);
        txtView3.setText(message3);
        txtView4.setText(message4);
        txtView5.setText(message5);
        txtView6.setText(message6);
        txtView7.setText(message7);
        txtView8.setText(message8);
        databaseadapter = new DatabaseAdapter(this);


    }

    public void confirm_modify(View view){

        c1 = txtView2.getText().toString();
        c2 = txtView3.getText().toString();

        s1 = txtView5.getText().toString();
        s2 = txtView6.getText().toString();

        float sellprice,costprice,profit = 0;
        costprice = Float.parseFloat(c1) * Float.parseFloat(c2);
        sellprice = Float.parseFloat(s1) * Float.parseFloat(s2);


        profit = sellprice - costprice;
        txtView7.setText(String.valueOf(sellprice));
        txtView8.setText(String.valueOf(profit));

        message = txtView.getText().toString();
        message1 = txtView1.getText().toString();
        message2 = txtView2.getText().toString();
        message3 = txtView3.getText().toString();
        message4 = txtView4.getText().toString();
        message5 = txtView5.getText().toString();
        message6 = txtView6.getText().toString();
        message7 = txtView7.getText().toString();
        message8 = txtView8.getText().toString();

        message1 = (Integer.parseInt(message3) - Integer.parseInt(message6)) + "";

        String s = txtView.getText().toString();
        databaseadapter.delrow(s);
        long id = databaseadapter.insertdata(message,Integer.parseInt(message1),Float.parseFloat(message2),Integer.parseInt(message3),Float.parseFloat(message4),Float.parseFloat(message5),Integer.parseInt(message6),Float.parseFloat(message7),Float.parseFloat(message8));
        if (id<0){
            Toast.makeText(this, "unsuccessfull", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this,"successfully updated a stock",Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(ModifyOrSell.this, MenuActivity.class));
        finish();

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
