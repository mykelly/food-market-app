package com.food.market.activity;

import android.app.Application;

/**
 * Created by Kelly Li on 2017-07-02.
 */

public class MarketApplication  extends Application{
    public static  MarketApplication marketApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        marketApplication = this;
    }
    public static MarketApplication getInstance(){
        return marketApplication;

    }
}
