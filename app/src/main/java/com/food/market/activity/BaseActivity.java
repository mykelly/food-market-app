package com.food.market.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kelly Li on 2017-07-02.
 */

public class BaseActivity  extends FragmentActivity{
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
