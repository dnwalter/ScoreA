package com.ousy.scorea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements SpinnerCallback, View.OnClickListener,
        RecyclerViewCallback, AppBarLayout.OnOffsetChangedListener
{
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.mainact_et_num)
    EditText etSearch;
    @BindView(R.id.mysp_type)
    MySpinner spType;
    @BindView(R.id.mysp_sort)
    MySpinner spSort;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_content)
    FloatingActionButton fabContent;
    @BindView(R.id.main_recyclerview_shares)
    RecyclerView mRecyclerView;
    private ScoreAdapter mAdapter;

    private List<String> mListType = new ArrayList<>();
    private List<String> mListSort = new ArrayList<>();

    private SharesInfoDao mDao;
    private List<SharesInfo> mSharesInfos = new ArrayList<>();

    private Unbinder mUnbinder;
    private float mAppBarHeight;
    private float mEtSearchWidth;
    // 上一次的verticalOffset
    private int mLastVerticalOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
        initSpinner();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void initData()
    {
        mDao = new SharesInfoDao(this);
        mSharesInfos = mDao.queryAll();
        SharesInfo sharesInfo = new SharesInfo();
        sharesInfo.setName("哈哈哈");
        sharesInfo.setScore(100);
        sharesInfo.setDate("2018-1");
        sharesInfo.setNum("222355");
        for (int i = 0; i < 9; i++)
        {
            mSharesInfos.add(sharesInfo);
        }
    }

    private void initView()
    {
        spType.setOnClickListener(this);
        spSort.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fabContent.setOnClickListener(this);

        mAdapter = new ScoreAdapter(this, mSharesInfos);
        mAdapter.setCallback(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAppBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {
                mAppBarLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                mAppBarHeight = mAppBarLayout.getMeasuredHeight();
                mEtSearchWidth = etSearch.getMeasuredWidth();
                Log.e("ousyh", mAppBarLayout.getMeasuredHeight() + ",");
                mAppBarLayout.addOnOffsetChangedListener(MainActivity.this);
            }
        });
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
                //                intent.setClass(this, ScrollingActivity.class);
                //                startActivity(intent);
                break;
            case R.id.fab_content:
                intent.setClass(this, ContentActivity.class);
                intent.putExtra("datas", (Serializable) mSharesInfos);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
    {
        if (mLastVerticalOffset != verticalOffset)
        {
            mLastVerticalOffset = verticalOffset;
            float scale = -verticalOffset / mAppBarHeight;
            int editWidth = (int) (mEtSearchWidth * (1 - scale));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) etSearch.getLayoutParams();
            params.width = editWidth;
            Log.e("ousyh", editWidth + "editwidth");
            etSearch.setLayoutParams(params);
        }
    }
}
