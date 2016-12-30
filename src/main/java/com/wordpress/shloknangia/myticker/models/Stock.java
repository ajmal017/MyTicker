package com.wordpress.shloknangia.myticker.models;

/**
 * Created by ssdn on 11/4/2016.
 */

public class Stock {

    String name;
    String symbol;
    String price;
    String change;
    boolean gain;

    public Stock(String name, String symbol, String price, String change, boolean gain) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.gain = gain;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getChange() {
        return change;
    }

    public boolean isGain() {
        return gain;
    }
}