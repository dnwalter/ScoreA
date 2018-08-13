package com.ousy.scorea.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ousy.scorea.models.SharesInfo;

import java.sql.SQLException;

/**
 * Created by ousyy on 2018/6/18.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "score.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper instance = null;

    /**
     * 全局单一实例
     *
     * @param context
     * @return DatabaseHelper instance
     */
    public static DatabaseHelper getInstance(Context context)
    {
        if (null == instance)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                {
                    instance = new DatabaseHelper(context);
                }
            }
        }

        return instance;
    }

    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            Log.i(TAG,"create table.");
            TableUtils.createTable(connectionSource, SharesInfo.class);
        } catch (SQLException e)
        {
            Log.e(TAG,e.getMessage()+",can't create table.");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {

    }
}
