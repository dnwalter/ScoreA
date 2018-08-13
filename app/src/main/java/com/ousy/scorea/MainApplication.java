package com.ousy.scorea;

import android.app.Application;
import android.content.Context;

import com.ousy.scorea.business.HintHelper;
import com.ousy.scorea.daos.DatabaseHelper;

/**
 * Created by ousyy on 2018/6/21.
 */

public class MainApplication extends Application
{
    private static Context sContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        sContext = getApplicationContext();
        HintHelper.getInstance(sContext);
        DatabaseHelper.getInstance(sContext);
    }

    public static Context getContext()
    {
        return sContext;
    }
}
