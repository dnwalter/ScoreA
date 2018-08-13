package com.ousy.scorea.daos;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.ousy.scorea.models.SharesInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ousyy on 2018/6/18.
 */

public class SharesInfoDao
{
    private Dao<SharesInfo, Integer> mDao = null;

    public SharesInfoDao(Context context)
    {
        if (null == mDao)
        {
            try
            {
                mDao = DatabaseHelper.getInstance(context).getDao(SharesInfo.class);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入一条记录
     *
     * @param shareInfo 用户信息
     */
    public void insert(SharesInfo shareInfo)
    {
        try
        {
            mDao.create(shareInfo);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 取出所有信息
     */
    public List<SharesInfo> queryAll()
    {
        List<SharesInfo> result = new ArrayList<>();
        try
        {
            result = mDao.queryForAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据股票号查询信息
     *
     * @param num 股票号
     * @return 信息
     */
    public List<SharesInfo> queryFromNum(String num)
    {
        List<SharesInfo> list = new ArrayList<>();
        QueryBuilder<SharesInfo, Integer> builder = mDao.queryBuilder();

        try
        {
            builder.orderBy("id", true).where().eq("num", num);
            list = mDao.query(builder.prepare());

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据行业查询信息
     *
     * @param type 行业
     * @return 信息
     */
    public List<SharesInfo> queryFromType(String type)
    {
        List<SharesInfo> list = new ArrayList<>();
        QueryBuilder<SharesInfo, Integer> builder = mDao.queryBuilder();

        try
        {
            builder.orderBy("score", true).where().eq("type", type);
            list = mDao.query(builder.prepare());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据id删除信息
     *
     * @param idList IDList
     */
    public void deleteFromIDList(List<Integer> idList)
    {
        DeleteBuilder builder = mDao.deleteBuilder();
        for (int i : idList)
        {
            deleteFromID(i);
        }
    }

    /**
     * 根据id删除信息
     *
     * @param id ID
     */
    public void deleteFromID(int id)
    {
        DeleteBuilder builder = mDao.deleteBuilder();
        try
        {
            builder.where().eq("id", id);
            mDao.delete(builder.prepare());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
