package com.wordpress.shloknangia.myticker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by SHLOK on 08-10-2016.
 */

public class DatabaseAdapter {
    Dbh dbh;

    public DatabaseAdapter(Context context) {
        dbh = new Dbh(context,null,null,5);
    }

    public long insertdata(String stockname, int noofstocks,float costrate,int stocksbought, float costprice, float sellrate, int stockssold,  float sellprice,float payoff){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(Dbh.COLUMN_STOCKNAME,stockname);
        contentvalues.put(Dbh.COLUMN_NO_OF_STOCK,noofstocks);
        contentvalues.put(Dbh.COLUMN_COSTRATE,costrate);
        contentvalues.put(Dbh.COLUMN_STOCKBOUGHT,stocksbought);
        contentvalues.put(Dbh.COLUMN_COSTPRICE,costprice);
        contentvalues.put(Dbh.COLUMN_SELLRATE,sellrate);
        contentvalues.put(Dbh.COLUMN_STOCKSOLD,stockssold);
        contentvalues.put(Dbh.COLUMN_SELLPRICE,sellprice);
        contentvalues.put(Dbh.COLUMN_PAYOFF,payoff);


        long id = db.insert(Dbh.TABLE_STOCK,null,contentvalues);
        db.close();
        return id;
    }

    public Cursor viewall(){
        SQLiteDatabase db = dbh.getWritableDatabase();

        String[] columns = {Dbh.COLUMN_ID,Dbh.COLUMN_STOCKNAME,Dbh.COLUMN_NO_OF_STOCK,Dbh.COLUMN_COSTRATE,Dbh.COLUMN_STOCKBOUGHT,Dbh.COLUMN_COSTPRICE,Dbh.COLUMN_SELLRATE,Dbh.COLUMN_STOCKSOLD,Dbh.COLUMN_SELLPRICE,Dbh.COLUMN_PAYOFF};
        Cursor cursor = db.query(Dbh.TABLE_STOCK, columns, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public String retcolstockname(){
        return Dbh.COLUMN_STOCKNAME;
    }

    public String retcolnoofstocks(){
        return Dbh.COLUMN_NO_OF_STOCK;
    }


    /*

    public List<String> retcolloc() {
        SQLiteDatabase db = dbh.getWritableDatabase();

        String[] columns = {Dbh.COLUMN_ID, Dbh.COLUMN_ITEMNAMEF, Dbh.COLUMN_ITEMNAMEL, Dbh.COLUMN_DESIG, Dbh.COLUMN_PHNO, Dbh.COLUMN_PHNO_ERP, Dbh.COLUMN_EMPNO, Dbh.COLUMN_LOC, Dbh.COLUMN_DPT, Dbh.COLUMN_DOB, Dbh.COLUMN_DOJN, Dbh.COLUMN_DOJL, Dbh.COLUMN_FAX, Dbh.COLUMN_ADDRESS, Dbh.COLUMN_EMAIL};
        Cursor cursor = db.query(Dbh.TABLE_ITEMS, columns, null, null, null, null, null);
        List<String> array = new ArrayList<String>();
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex(Dbh.COLUMN_LOC));
            array.add(uname);
        }
        return array;
    }

    public List<String> retcoldpt() {
        SQLiteDatabase db = dbh.getWritableDatabase();

        String[] columns = {Dbh.COLUMN_ID, Dbh.COLUMN_ITEMNAMEF, Dbh.COLUMN_ITEMNAMEL, Dbh.COLUMN_DESIG, Dbh.COLUMN_PHNO, Dbh.COLUMN_PHNO_ERP, Dbh.COLUMN_EMPNO, Dbh.COLUMN_LOC, Dbh.COLUMN_DPT, Dbh.COLUMN_DOB, Dbh.COLUMN_DOJN, Dbh.COLUMN_DOJL, Dbh.COLUMN_FAX, Dbh.COLUMN_ADDRESS, Dbh.COLUMN_EMAIL};
        Cursor cursor = db.query(Dbh.TABLE_ITEMS, columns, null, null, null, null, null);
        List<String> array = new ArrayList<String>();
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex(Dbh.COLUMN_DPT));
            array.add(uname);
        }
        return array;
    }
    public List<String> retcoldesig() {
        SQLiteDatabase db = dbh.getWritableDatabase();

        String[] columns = {Dbh.COLUMN_ID, Dbh.COLUMN_ITEMNAMEF, Dbh.COLUMN_ITEMNAMEL, Dbh.COLUMN_DESIG, Dbh.COLUMN_PHNO, Dbh.COLUMN_PHNO_ERP, Dbh.COLUMN_EMPNO, Dbh.COLUMN_LOC, Dbh.COLUMN_DPT, Dbh.COLUMN_DOB, Dbh.COLUMN_DOJN, Dbh.COLUMN_DOJL, Dbh.COLUMN_FAX, Dbh.COLUMN_ADDRESS, Dbh.COLUMN_EMAIL};
        Cursor cursor = db.query(Dbh.TABLE_ITEMS, columns, null, null, null, null, null);
        List<String> array = new ArrayList<String>();
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex(Dbh.COLUMN_DESIG));
            array.add(uname);
        }
        return array;
    }

    public List<String> retcoleno() {
        SQLiteDatabase db = dbh.getWritableDatabase();

        String[] columns = {Dbh.COLUMN_ID, Dbh.COLUMN_ITEMNAMEF, Dbh.COLUMN_ITEMNAMEL, Dbh.COLUMN_DESIG, Dbh.COLUMN_PHNO, Dbh.COLUMN_PHNO_ERP, Dbh.COLUMN_EMPNO, Dbh.COLUMN_LOC, Dbh.COLUMN_DPT, Dbh.COLUMN_DOB, Dbh.COLUMN_DOJN, Dbh.COLUMN_DOJL, Dbh.COLUMN_FAX, Dbh.COLUMN_ADDRESS, Dbh.COLUMN_EMAIL};
        Cursor cursor = db.query(Dbh.TABLE_ITEMS, columns, null, null, null, null, null);
        List<String> array = new ArrayList<String>();
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex(Dbh.COLUMN_EMPNO));
            array.add(uname);
        }
        return array;
    }*/


    /*
    public String retcolnamel(){
        return Dbh.COLUMN_ITEMNAMEL;
    }
    public String retdesig(){
        return Dbh.COLUMN_DESIG;
    }
    public String retempno(){return Dbh.COLUMN_EMPNO;}
    public String retloc(){return Dbh.COLUMN_LOC;}
    public String retdpt(){return Dbh.COLUMN_DPT;}*/




    public String[] setvalue(String data){
        SQLiteDatabase db = dbh.getWritableDatabase();
        String[] args = new String[]{"","","","","","","","",""};
        String[] columns = {Dbh.COLUMN_ID,Dbh.COLUMN_STOCKNAME,Dbh.COLUMN_NO_OF_STOCK,Dbh.COLUMN_COSTRATE,Dbh.COLUMN_STOCKBOUGHT,Dbh.COLUMN_COSTPRICE,Dbh.COLUMN_SELLRATE,Dbh.COLUMN_STOCKSOLD,Dbh.COLUMN_SELLPRICE,Dbh.COLUMN_PAYOFF};
        Cursor cursor = db.query(Dbh.TABLE_STOCK, columns, Dbh.COLUMN_ID + " = '" + data + "'", null, null, null, null);
        while(cursor.moveToNext()) {

            int index0 = cursor.getColumnIndex(Dbh.COLUMN_STOCKNAME);
            int index1 = cursor.getColumnIndex(Dbh.COLUMN_NO_OF_STOCK);
            int index2 = cursor.getColumnIndex(Dbh.COLUMN_COSTRATE);
            int index3 = cursor.getColumnIndex(Dbh.COLUMN_STOCKBOUGHT);
            int index4 = cursor.getColumnIndex(Dbh.COLUMN_COSTPRICE);
            int index5 = cursor.getColumnIndex(Dbh.COLUMN_SELLRATE);
            int index6 = cursor.getColumnIndex(Dbh.COLUMN_STOCKSOLD);
            int index7 = cursor.getColumnIndex(Dbh.COLUMN_SELLPRICE);
            int index8 = cursor.getColumnIndex(Dbh.COLUMN_PAYOFF);

            String stockname = cursor.getString(index0);
            String noofstocks = cursor.getString(index1);
            String costrate = cursor.getString(index2);
            String stocksbought = cursor.getString(index3);
            String costprice = cursor.getString(index4);
            String sellrate = cursor.getString(index5);
            String stockssold = cursor.getString(index6);
            String sellprice = cursor.getString(index7);
            String payoff = cursor.getString(index8);

            args[0] = stockname;
            args[1] = noofstocks;
            args[2] = costrate;
            args[3] = stocksbought;
            args[4] = costprice;
            args[5] = sellrate;
            args[6] = stockssold;
            args[7] = sellprice;
            args[8] = payoff;


        }
        cursor.close();
        return args;

    }

    public void delrow(String stockname){

        SQLiteDatabase db = dbh.getWritableDatabase();
        String[] whereArgs = {stockname};
        db.delete(Dbh.TABLE_STOCK,Dbh.COLUMN_STOCKNAME + " =?",whereArgs);

    }


    static class Dbh  extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 5;//update database version number whenever update database
        private static final String DATABASE_NAME = "stock.db"; //name of database
        private static final String TABLE_STOCK ="stock";//name of table
        private static final String COLUMN_ID = "_id"; //coloumn for each attribute
        private static final String COLUMN_STOCKNAME = "stockname"; //column stockname 
        private static final String COLUMN_NO_OF_STOCK = "no_of_stock";
        private static final String COLUMN_COSTRATE = "costrate";
        private static final String COLUMN_STOCKBOUGHT = "stockbought";
        private static final String COLUMN_COSTPRICE = "costprice";
        private static final String COLUMN_SELLRATE = "sellrate";
        private static final String COLUMN_STOCKSOLD = "stocksold";
        private static final String COLUMN_SELLPRICE = "sellprice";
        private static final String COLUMN_PAYOFF = "payoff";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_STOCK + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STOCKNAME + " VARCHAR(225), " +
                COLUMN_NO_OF_STOCK + " INTEGER, " +
                COLUMN_COSTRATE + " REAL, " +
                COLUMN_STOCKBOUGHT + " INTEGER, " +
                COLUMN_COSTPRICE + " REAL, " +
                COLUMN_SELLRATE + " REAL, " +
                COLUMN_STOCKSOLD + " INTEGER, " +
                COLUMN_SELLPRICE +  " REAL, " +
                COLUMN_PAYOFF + " REAL);";
        private Context context;
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_STOCK;

        public Dbh(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch(SQLException e){
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
            }catch(SQLException e){
                Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}


