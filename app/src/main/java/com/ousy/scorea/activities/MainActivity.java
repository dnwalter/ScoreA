package com.ousy.scorea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ousy.scorea.R;
import com.ousy.scorea.adapters.RecyclerViewCallback;
import com.ousy.scorea.adapters.ScoreAdapter;
import com.ousy.scorea.daos.SharesInfoDao;
import com.ousy.scorea.models.SharesInfo;
import com.ousy.scorea.views.MySpinner;
import com.ousy.scorea.views.SpinnerCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SpinnerCallback, View.OnClickListener,
        RecyclerViewCallback
{
    private MySpinner spType;
    private MySpinner spSort;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabContent;
    private RecyclerView mRecyclerView;
    private ScoreAdapter mAdapter;

    private List<String> mListType = new ArrayList<>();
    private List<String> mListSort = new ArrayList<>();

    private SharesInfoDao mDao;
    private List<SharesInfo> mSharesInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
        initSpinner();
        initEvent();
    }

    private void initData()
    {
        mDao = new SharesInfoDao(this);
        mSharesInfos = mDao.queryAll();
    }

    private void initView()
    {
        spType = (MySpinner) findViewById(R.id.mysp_type);
        spSort = (MySpinner) findViewById(R.id.mysp_sort);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        fabContent = (FloatingActionButton) findViewById(R.id.fab_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview_shares);
    }

    private void initSpinner()
    {
        mListType.add("全部");
        mListType.add("钢铁");
        spType.initSpinner(true, R.drawable.ic_tag, R.id.mysp_type, this, mListType);

        mListSort.add("对比");
        mListSort.add("分数");
        spSort.initSpinner(false, R.drawable.ic_sort, R.id.mysp_sort, this, mListSort);
    }

    private void initEvent()
    {
        spType.setOnClickListener(this);
        spSort.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fabContent.setOnClickListener(this);

        mAdapter = new ScoreAdapter(this, mSharesInfos);
        mAdapter.setCallback(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItem(int viewId, int position)
    {
        if (viewId == R.id.mysp_sort)
        {
            switch (position)
            {
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
            Log.e("ousyxx", mListSort.get(position));
        }
        else
        {
            Log.e("ousyxx", mListType.get(position));
        }
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.mysp_sort:
                spSort.onClick();
                break;
            case R.id.mysp_type:
                spType.onClick();
                break;
            case R.id.fab_add:
                intent.setClass(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_content:
                intent.setClass(this, ContentActivity.class);
                intent.putExtra("datas", (Serializable)mSharesInfos );
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemViewClick(int viewId, int position)
    {
        Log.e("ousyxx", "test" + position);
    }
}
