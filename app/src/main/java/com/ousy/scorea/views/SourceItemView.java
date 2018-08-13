package com.ousy.scorea.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ousy.scorea.R;
import com.ousy.scorea.business.BtnCountTag;
import com.ousy.scorea.business.SourceFun;

/**
 * Created by ousyy on 2018/6/21.
 */

public class SourceItemView extends LinearLayout
{
    private TextView tvTitle;
    private EditText etNow;
    private EditText etLast;
    private EditText etYearOne1;
    private EditText etYearOne2;
    private EditText etYearOne3;
    private EditText etYearTwo1;
    private EditText etYearTwo2;
    private EditText etYearTwo3;
    private RelativeLayout rltyYearOne;
    private RelativeLayout rltyYearTwo;
    private EditText etSource;
    private Button btnCount;
    private TypedArray mTypedArray;
    private SourceFun mSourceFun;

    public SourceItemView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.addact_item_source, this, true);
        mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SourceItemView);
        mSourceFun = new SourceFun();
        initView();
        initRlyt();
        initEditText();
        initViewHint();
        setButtonTag();
    }

    private void initView()
    {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etNow = (EditText) findViewById(R.id.et_now);
        etLast = (EditText) findViewById(R.id.et_last);
        etYearOne1 = (EditText) findViewById(R.id.et_year_one1);
        etYearOne2 = (EditText) findViewById(R.id.et_year_one2);
        etYearOne3 = (EditText) findViewById(R.id.et_year_one3);
        etYearTwo1 = (EditText) findViewById(R.id.et_year_two1);
        etYearTwo2 = (EditText) findViewById(R.id.et_year_two2);
        etYearTwo3 = (EditText) findViewById(R.id.et_year_two3);
        rltyYearOne = (RelativeLayout) findViewById(R.id.rlyt_year_one);
        rltyYearTwo = (RelativeLayout) findViewById(R.id.rlyt_year_two);
        etSource = (EditText) findViewById(R.id.addact_et_source);
        btnCount = (Button) findViewById(R.id.btn_count);
    }

    // 右侧两行输入框是否显示
    private void initRlyt()
    {
        boolean rlyt1 = mTypedArray.getBoolean(R.styleable.SourceItemView_yearOneVisibility, false);
        boolean rlyt2 = mTypedArray.getBoolean(R.styleable.SourceItemView_yearTwoVisibility, false);
        setV(rltyYearOne, rlyt1);
        setV(rltyYearTwo, rlyt2);
    }

    private void initEditText()
    {
        boolean nowEnable = mTypedArray.getBoolean(R.styleable.SourceItemView_nowEnable, true);
        boolean sourceEnable = mTypedArray.getBoolean(R.styleable.SourceItemView_sourceEnable, false);
        boolean lastV = mTypedArray.getBoolean(R.styleable.SourceItemView_lastVisibility, false);
        boolean yearOne2V = mTypedArray.getBoolean(R.styleable.SourceItemView_yearOne2Visibility, true);
        boolean yearOne3V = mTypedArray.getBoolean(R.styleable.SourceItemView_yearOne3Visibility, true);

        etNow.setEnabled(nowEnable);
        etSource.setEnabled(sourceEnable);
        setV(etLast, lastV);
        setV(etYearOne2, yearOne2V);
        setV(etYearOne3, yearOne3V);
    }

    // 输入框提示语
    private void initViewHint()
    {
        String nowHint = mTypedArray.getString(R.styleable.SourceItemView_nowHint);
        String title = mTypedArray.getString(R.styleable.SourceItemView_title);

        etNow.setHint(nowHint);
        tvTitle.setText(title);
    }

    private void setButtonTag()
    {
        int btnTag = mTypedArray.getInteger(R.styleable.SourceItemView_buttonTag, 0);
        btnCount.setTag(btnTag);
    }

    // 设置控件是否可视
    private void setV(View view, boolean sign)
    {
        if (sign)
        {
            view.setVisibility(VISIBLE);
        }
        else
        {
            view.setVisibility(GONE);
        }
    }

    public int viewCount(int tag)
    {
        int count = 0;
        switch (tag)
        {
            case BtnCountTag.TAG_INCOME:
                count = incomeCount();
                break;
            case BtnCountTag.TAG_PROFIT:
                count = profitCount();
                break;
            case BtnCountTag.TAG_MARGIN:
                count = marginCount();
                break;
            case BtnCountTag.TAG_INVENTORY:
                count = inventoryCount();
                break;
            case BtnCountTag.TAG_CASH:
                count = cashCount();
                break;
            case BtnCountTag.TAG_RETURN:
                count = returnCount();
                break;
            case BtnCountTag.TAG_REWARD:
                count = rewardCount();
                break;
            case BtnCountTag.TAG_PBV:
                count = PBVCount();
                break;
            case BtnCountTag.TAG_PEG:
                count = PEGCount();
                break;
            default:
                break;
        }

        return count;
    }

    private int incomeCount()
    {
        int count = 0;
        float income = getFloat(etNow);
        count = mSourceFun.incomeFun(income);
        etSource.setText(count + "");

        return count;
    }

    private int profitCount()
    {
        int count = 0;
        float profit = getFloat(etNow);
        float lastProfit = getFloat(etLast);
        count = mSourceFun.profitFun(profit, lastProfit);
        etSource.setText(count + "");

        return count;
    }

    private int marginCount()
    {
        int count = 0;
        float margin = getFloat(etNow);
        float margin1 = getFloat(etYearOne1);
        float margin2 = getFloat(etYearOne2);
        float margin3 = getFloat(etYearOne3);
        count = mSourceFun.marginFun(margin, margin1, margin2, margin3);
        etSource.setText(count + "");

        return count;
    }

    private int inventoryCount()
    {
        int count = 0;
        float inven = getFloat(etNow);
        float lastInven = getFloat(etLast);
        float inven1 = getFloat(etYearOne1);
        float inven2 = getFloat(etYearOne2);
        float inven3 = getFloat(etYearOne3);
        count = mSourceFun.inventoryFun(inven, lastInven, inven1, inven2, inven3);
        etSource.setText(count + "");

        return count;
    }

    private int cashCount()
    {
        int count = 0;
        float cash1 = getFloat(etYearOne1);
        float cash2 = getFloat(etYearOne2);
        float cash3 = getFloat(etYearOne3);
        float incomeGu1 = getFloat(etYearTwo1);
        float incomeGu2 = getFloat(etYearTwo2);
        float incomeGu3 = getFloat(etYearTwo3);
        count = mSourceFun.cashFun(cash1, cash2, cash3, incomeGu1, incomeGu2, incomeGu3);
        etSource.setText(count + "");

        return count;
    }

    private int returnCount()
    {
        int count = 0;
        float returnRate = getFloat(etNow);
        float lastReturnRate = getFloat(etLast);
        float returnRate1 = getFloat(etYearOne1);
        count = mSourceFun.returnFun(returnRate, lastReturnRate, returnRate1);
        etSource.setText(count + "");

        return count;
    }

    private int rewardCount()
    {
        int count = 0;
        float reward = getFloat(etNow);
        float lastReward = getFloat(etLast);
        float reward1 = getFloat(etYearOne1);
        count = mSourceFun.rewardFun(reward, lastReward, reward1);
        etSource.setText(count + "");

        return count;
    }

    private int PBVCount()
    {
        int count = 0;
        float pbv = getFloat(etNow);
        count = mSourceFun.pbvFun(pbv);
        etSource.setText(count + "");

        return count;
    }

    private int PEGCount()
    {
        int count = 0;
        float pe = getFloat(etNow);
        float profit1 = getFloat(etYearOne1);
        float profit2 = getFloat(etYearOne2);
        float profit3 = getFloat(etYearOne3);
        count = mSourceFun.pegFun(pe, profit1, profit2, profit3);
        etSource.setText(count + "");

        return count;
    }

    // 若输入框为空返回0
    private float getFloat(EditText editText)
    {
        String str = editText.getText().toString();
        if (str.equals(""))
        {
            return 0;
        }
        else
        {
            return Float.parseFloat(str);
        }
    }
}
