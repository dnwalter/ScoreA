package com.ousy.scorea.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ousy.scorea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义下拉列表
 * Created by ousyy on 2018/6/18.
 */

public class MySpinner extends LinearLayout
{
    private Context mContext;

    private Spinner spContent;
    private ImageView ivIcon;
    private TextView tvTag;

    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private SpinnerCallback mCallback;
    private int mViewId = 0;

    public MySpinner(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.spinner_my, this, true);

        initView();
        initOnItem();
    }

    private void initView()
    {
        spContent = (Spinner) findViewById(R.id.spinner_content);
        ivIcon = (ImageView) findViewById(R.id.spinner_iv_icon);
        tvTag = (TextView) findViewById(R.id.spinner_tv_tag);
    }

    private void initOnItem()
    {
        spContent.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
            //选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                tvTag.setText(mList.get(arg2));//文本说明
                mCallback.onItem(mViewId, arg2);

            }

            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }

    public void initSpinner(boolean isTag, int imgId, int viewId, SpinnerCallback callback, List list)
    {
        setList(list);
        setIsTag(isTag);
        setImg(imgId);
        setCallback(viewId, callback);
    }

    public void setIsTag(boolean isTag)
    {
        if (isTag)
        {
            tvTag.setVisibility(VISIBLE);
            tvTag.setText(mList.get(0));
        }
        else
        {
            tvTag.setVisibility(GONE);
        }
    }

    public void setImg(int imgId)
    {
        ivIcon.setImageDrawable(ContextCompat.getDrawable(mContext, imgId));
    }

    public void setCallback(int viewId, SpinnerCallback callback)
    {
        mViewId = viewId;
        mCallback = callback;
    }

    public void setList(List list)
    {
        mList = list;
        //适配器
        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, mList);
        //设置样式
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spContent.setAdapter(mAdapter);

        tvTag.setText(mList.get(0));
    }

    public void onClick()
    {
        spContent.performClick();
    }
}
