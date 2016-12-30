package com.wordpress.shloknangia.myticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStock extends AppCompatActivity {
    EditText name_of_stock,cost_price,noof_stock,total_cost;
    String symbol,rate;

    DatabaseAdapter databaseadapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name_of_stock= (EditText)findViewById(R.id.text_stock_name);
        cost_price=(EditText)findViewById(R.id.text_cost_price);
        noof_stock=(EditText)findViewById(R.id.text_noofstock);
        total_cost=(EditText)findViewById(R.id.text_cost);

        Intent intent = getIntent();
        symbol = intent.getStringExtra("symbol");
        rate = intent.getStringExtra("rate");

        name_of_stock.setText(symbol);
        cost_price.setText(rate);
        databaseadapter = new DatabaseAdapter(this);

        noof_stock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!noof_stock.getText().toString().isEmpty()){
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void calculate(){
        String r,q;
        float rate,totalprice;
        int quan;
        q = noof_stock.getText().toString();
        quan = Integer.parseInt(q);
        r = cost_price.getText().toString();
        rate =Float.parseFloat(r);
        totalprice = quan*rate;
//        return totalprice;
        total_cost.setText(String.valueOf(totalprice));

    }
    
    public void confirm_add(View view){
        String stockname,s1,s2,s3;
        int numstock;
        float costprice,costrate;

        stockname = name_of_stock.getText().toString();
        s1 = noof_stock.getText().toString();
        numstock = Integer.parseInt(s1);
        s2 = cost_price.getText().toString();
        costrate = Float.parseFloat(s2);
        s3 = total_cost.getText().toString();
        costprice = Float.parseFloat(s3);

        long id = databaseadapter.insertdata(stockname,numstock,costrate,numstock,costprice,0.0f,0,0.0f,0.0f);
        if (id<0){
            Toast.makeText(this, "unsuccessfull",Toast.LENGTH_SHORT).show();
        }else
        {
           Toast.makeText(this,"successfully inserted a stock",Toast.LENGTH_SHORT).show();
        }

        this.finish();
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
