package com.ousy.scorea.business;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

import com.ousy.scorea.MainApplication;

import java.lang.reflect.Method;

/**
 * Created by ousyy on 2018/6/24.
 */

public class PhoneHelper
{
    public PhoneHelper(){}
    /**
     * 获取底部虚拟键盘的高度
     */
    public int getBottomKeyboardHeight(Activity activity){
        int screenHeight =  getAccurateScreenDpi(activity)[1];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }

    /**
     * 获取精确的屏幕大小
     */
    public int[] getAccurateScreenDpi(Activity activity)
    {
        int[] screenWH = new int[2];
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return screenWH;
    }
}
