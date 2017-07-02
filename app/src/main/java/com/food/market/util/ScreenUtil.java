package com.food.market.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.food.market.activity.MarketApplication;
import com.food.market.data.Constant;

import java.lang.reflect.Field;

public class ScreenUtil {
    public static int SCREEN_PX_WIDTH = 0;
    public static int SCREEN_PX_HEIGHT = 0;
    public static float SCREEN_DP_WIDTH;
    public static float SCREEN_DP_HEIGHT;
    public static float DENSITY = -1;

    public static float getDensity(Activity activity) {
        if (DENSITY == -1) {
            DENSITY = activity.getResources().getDisplayMetrics().density;
        }
        return DENSITY;
    }
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context)
    {
        //屏幕宽度
        int screenWidth = 0;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();

        //获取手机系统版本信息
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {//  sdk version>=API3
            Point outSize = new Point();
            defaultDisplay.getSize(outSize);
            screenWidth = outSize.x;
        } else
        {//  <api13
            screenWidth = defaultDisplay.getWidth();
        }
        return screenWidth;
    }
    public static int getScreenWidthPixel(Activity activity) {
        SCREEN_PX_WIDTH = activity.getWindowManager().getDefaultDisplay()
                .getWidth();
        SCREEN_DP_WIDTH = SCREEN_PX_WIDTH / getDensity(activity);
        MarketApplication.getInstance()
                .getSharedPreferences(Constant.SCREENINFO, Context.MODE_PRIVATE)
                .edit().putInt("width", SCREEN_PX_WIDTH).commit();
        return SCREEN_PX_WIDTH;
    }
    public static int getStatusBarHeightPixel(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return sbar;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = MarketApplication.getInstance()
                .getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MarketApplication.getInstance()
                .getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取版本号(内部识别号)
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public static int[] getViewAbsCoord(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);// 获取在整个屏幕内的绝对坐标
        return location;

    }

    public static int[] getViewAbsWindow(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);// 获取在父组件的坐标
        return location;

    }

}
