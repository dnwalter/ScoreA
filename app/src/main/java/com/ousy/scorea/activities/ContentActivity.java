package com.ousy.scorea.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.ousy.scorea.R;
import com.ousy.scorea.adapters.ContentAdapter;
import com.ousy.scorea.adapters.ContentNameAdapter;
import com.ousy.scorea.models.SharesInfo;
import com.ousy.scorea.views.CustomHScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ousyy on 2018/6/30.
 */

public class ContentActivity extends AppCompatActivity
{
    private CustomHScrollView hsvTitle;
    private CustomHScrollView hsvContent;
    private RecyclerView rvContent;
    private RecyclerView rvName;
    private ContentAdapter mContentAdapter;
    private ContentNameAdapter mNameAdapter;
    private List<SharesInfo> mSharesInfos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initData();
        initView();
        initEvent();
    }

    private void initData()
    {
        Intent intentGet = getIntent();
        mSharesInfos = (List<SharesInfo>) intentGet.getSerializableExtra("datas");
//        SharesInfo sharesInfo = mSharesInfos.get(0);
//        for (int i = 0; i < 20; i++)
//        {
//            mSharesInfos.add(sharesInfo);
//        }
    }

    private void initView()
    {
        hsvTitle = (CustomHScrollView) findViewById(R.id.conact_hsv_title);
        hsvContent = (CustomHScrollView) findViewById(R.id.conact_hsv_content);

        rvContent = (RecyclerView) findViewById(R.id.conact_rv_content);
        rvName = (RecyclerView) findViewById(R.id.conact_rv_name);
    }

    private void initEvent()
    {
        hsvTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                //不能滑动
                return true;
            }
        });
        hsvContent.setScrollView(hsvTitle);

        mContentAdapter = new ContentAdapter(this, mSharesInfos);
        rvContent.setAdapter(mContentAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this));

        mNameAdapter = new ContentNameAdapter(this,mSharesInfos);
        rvName.setAdapter(mNameAdapter);
        rvName.setLayoutManager(new LinearLayoutManager(this));

        rvName.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rvContent.scrollBy(dx, dy);
                }
            }
        });

        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rvName.scrollBy(dx, dy);
                }
            }
        });

    }

    /**
     * 返回true，表示拦截事件。
     * 返回false，表示不做任何处理，交给子View处理。
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        /**
         * 如果用户的手指同时放在屏幕上滑动，不要触发滚动事件。
         *
         */
        if (event.getPointerCount() >= 2) {
            return true;
        }

        /**
         * 如果左侧的RecyclerView1在滚动中，但是此时用户又在RecyclerView2中触发滚动事件，则停止所有滚动，等待新一轮滚动。
         *
         */
        if (rvName.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
            if (touchEventInView(rvContent, event.getX(), event.getY())) {
                rvName.stopScroll();
                rvContent.stopScroll();
                return true;
            }
        }

        /**
         * 如果右侧的RecyclerView2在滚动中，但是此时用户又在RecyclerView1中触发滚动事件，则停止所有滚动，等待新一轮滚动。
         *
         */
        if (rvContent.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
            if (touchEventInView(rvName, event.getX(), event.getY())) {
                rvContent.stopScroll();
                rvName.stopScroll();
                return true;
            }
        }

        return super.dispatchTouchEvent(event);
    }

    private boolean touchEventInView(View view, float x, float y) {
        if (view == null) {
            return false;
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        if (y >= top && y <= bottom && x >= left && x <= right) {
            return true;
        }

        return false;
    }

}
