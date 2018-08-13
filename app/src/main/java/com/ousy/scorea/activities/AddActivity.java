package com.ousy.scorea.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ousy.scorea.R;
import com.ousy.scorea.business.BtnCountTag;
import com.ousy.scorea.business.HintHelper;
import com.ousy.scorea.business.PhoneHelper;
import com.ousy.scorea.business.SourceFun;
import com.ousy.scorea.business.WindowSizeHelper;
import com.ousy.scorea.daos.SharesInfoDao;
import com.ousy.scorea.models.SharesInfo;
import com.ousy.scorea.views.SourceItemView;

/**
 * Created by ousyy on 2018/6/21.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView ivBack;
    private ImageView ivSave;
    private TextView tvSource;
    private EditText etName;
    private EditText etNum;
    private EditText etDate;
    private EditText etType;
    private SourceItemView sivIncome;
    private SourceItemView sivProfit;
    private SourceItemView sivMargin;
    private SourceItemView sivInventory;
    private SourceItemView sivCash;
    private SourceItemView sivReturn;
    private SourceItemView sivReward;
    private SourceItemView sivPBV;
    private SourceItemView sivPEG;
    private View sivCost;
    private Button btnIncome;
    private Button btnProfit;
    private Button btnMargin;
    private Button btnCost;
    private Button btnInventory;
    private Button btnCash;
    private Button btnReturn;
    private Button btnReward;
    private Button btnPBV;
    private Button btnPEG;

    private SharesInfo mInfo;
    private SourceFun mSourceFun;
    private int mScore = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mInfo = new SharesInfo();
        mSourceFun = new SourceFun();

        initView();
        initEvent();
    }

    private void initView()
    {
        ivBack = (ImageView) findViewById(R.id.addact_iv_back);
        ivSave = (ImageView) findViewById(R.id.addact_iv_save);
        tvSource = (TextView) findViewById(R.id.addact_tv_soucre);
        etName = (EditText) findViewById(R.id.addact_et_name);
        etNum = (EditText) findViewById(R.id.addact_et_num);
        etDate = (EditText) findViewById(R.id.addact_et_date);
        etType = (EditText) findViewById(R.id.addact_et_type);
        sivIncome = (SourceItemView) findViewById(R.id.siv_income);
        sivProfit = (SourceItemView) findViewById(R.id.siv_profit);
        sivMargin = (SourceItemView) findViewById(R.id.siv_margin);
        sivInventory = (SourceItemView) findViewById(R.id.siv_inventory);
        sivCash = (SourceItemView) findViewById(R.id.siv_cash);
        sivReturn = (SourceItemView) findViewById(R.id.siv_return);
        sivReward = (SourceItemView) findViewById(R.id.siv_reward);
        sivPBV = (SourceItemView) findViewById(R.id.siv_pbv);
        sivPEG = (SourceItemView) findViewById(R.id.siv_peg);
        btnIncome = (Button) sivIncome.findViewById(R.id.btn_count);
        btnProfit = (Button) sivProfit.findViewById(R.id.btn_count);
        btnMargin = (Button) sivMargin.findViewById(R.id.btn_count);
        btnInventory = (Button) sivInventory.findViewById(R.id.btn_count);
        btnCash = (Button) sivCash.findViewById(R.id.btn_count);
        btnReturn = (Button) sivReturn.findViewById(R.id.btn_count);
        btnReward = (Button) sivReward.findViewById(R.id.btn_count);
        btnPBV = (Button) sivPBV.findViewById(R.id.btn_count);
        btnPEG = (Button) sivPEG.findViewById(R.id.btn_count);
        sivCost = findViewById(R.id.siv_cost);
        btnCost = (Button) sivCost.findViewById(R.id.btn_count);
        btnCost.setTag(BtnCountTag.TAG_COST);
    }

    private void initEvent()
    {
        ivBack.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        btnIncome.setOnClickListener(this);
        btnProfit.setOnClickListener(this);
        btnMargin.setOnClickListener(this);
        btnCost.setOnClickListener(this);
        btnInventory.setOnClickListener(this);
        btnCash.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        btnReward.setOnClickListener(this);
        btnPBV.setOnClickListener(this);
        btnPEG.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addact_iv_back:
                isFinsh();
                break;
            case R.id.addact_iv_save:
                saveScore();
                break;
            case R.id.btn_count:
                btnCountOnClick(v);
                break;
            default:
                break;
        }
    }

    private void saveScore()
    {
        mInfo.setName(etName.getText().toString());
        mInfo.setNum(etNum.getText().toString());
        mInfo.setDate(etDate.getText().toString());
        mInfo.setType(etType.getText().toString());
        mInfo.setScore(mScore);
        SharesInfoDao dao = new SharesInfoDao(this);
        dao.insert(mInfo);
        finish();
    }

    private void btnCountOnClick(View view)
    {
        int tag = (int) view.getTag();
        switch (tag)
        {
            case BtnCountTag.TAG_INCOME:
                mInfo.setIncomeRate(sivIncome.viewCount(tag));
                break;
            case BtnCountTag.TAG_PROFIT:
                mInfo.setProfitRate(sivProfit.viewCount(tag));
                break;
            case BtnCountTag.TAG_MARGIN:
                mInfo.setMargin(sivMargin.viewCount(tag));
                break;
            case BtnCountTag.TAG_COST:
                mInfo.setCostRate(costCount());
                break;
            case BtnCountTag.TAG_INVENTORY:
                mInfo.setInventoryRate(sivInventory.viewCount(tag));
                break;
            case BtnCountTag.TAG_CASH:
                mInfo.setCashFlow(sivCash.viewCount(tag));
                break;
            case BtnCountTag.TAG_RETURN:
                mInfo.setReturnRate(sivReturn.viewCount(tag));
                break;
            case BtnCountTag.TAG_REWARD:
                mInfo.setRewardRate(sivReward.viewCount(tag));
                break;
            case BtnCountTag.TAG_PBV:
                mInfo.setPbv(sivPBV.viewCount(tag));
                break;
            case BtnCountTag.TAG_PEG:
                mInfo.setPeg(sivPEG.viewCount(tag));
                break;
            default:
                break;
        }
        setScore();
    }

    private int costCount()
    {
        int count = 0;
        EditText[][] editTexts = new EditText[4][4];
        EditText etSource = (EditText) sivCost.findViewById(R.id.addact_et_source);
        View nowView = sivCost.findViewById(R.id.item_cost_now);
        View view1 = sivCost.findViewById(R.id.item_cost_1);
        View view2 = sivCost.findViewById(R.id.item_cost_2);
        View view3 = sivCost.findViewById(R.id.item_cost_3);
        EditText etSCost = (EditText) nowView.findViewById(R.id.et_sale);
        EditText etMCost = (EditText) nowView.findViewById(R.id.et_m);
        EditText etFCost = (EditText) nowView.findViewById(R.id.et_f);
        EditText etIncome = (EditText) nowView.findViewById(R.id.et_income);
        editTexts[0][0] = etSCost;
        editTexts[0][1] = etMCost;
        editTexts[0][2] = etFCost;
        editTexts[0][3] = etIncome;

        EditText etSCost1 = (EditText) view1.findViewById(R.id.et_sale);
        EditText etMCost1 = (EditText) view1.findViewById(R.id.et_m);
        EditText etFCost1 = (EditText) view1.findViewById(R.id.et_f);
        EditText etIncome1 = (EditText) view1.findViewById(R.id.et_income);
        editTexts[1][0] = etSCost1;
        editTexts[1][1] = etMCost1;
        editTexts[1][2] = etFCost1;
        editTexts[1][3] = etIncome1;

        EditText etSCost2 = (EditText) view2.findViewById(R.id.et_sale);
        EditText etMCost2 = (EditText) view2.findViewById(R.id.et_m);
        EditText etFCost2 = (EditText) view2.findViewById(R.id.et_f);
        EditText etIncome2 = (EditText) view2.findViewById(R.id.et_income);
        editTexts[2][0] = etSCost2;
        editTexts[2][1] = etMCost2;
        editTexts[2][2] = etFCost2;
        editTexts[2][3] = etIncome2;

        EditText etSCost3 = (EditText) view3.findViewById(R.id.et_sale);
        EditText etMCost3 = (EditText) view3.findViewById(R.id.et_m);
        EditText etFCost3 = (EditText) view3.findViewById(R.id.et_f);
        EditText etIncome3 = (EditText) view3.findViewById(R.id.et_income);
        editTexts[3][0] = etSCost3;
        editTexts[3][1] = etMCost3;
        editTexts[3][2] = etFCost3;
        editTexts[3][3] = etIncome3;

        float[] costRates = new float[4];
        for (int i = 0; i < 4; i++)
        {
            costRates[i] = mSourceFun.costRate(getFloat(editTexts[i][0]),getFloat(editTexts[i][1]),getFloat(editTexts[i][2]),getFloat(editTexts[i][3]));
        }

        count = mSourceFun.costFun(costRates[0],costRates[1],costRates[2],costRates[3]);
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

    private void setScore()
    {
        mScore = mInfo.getIncomeRate() + mInfo.getProfitRate() + mInfo.getMargin() + mInfo.getCostRate() + mInfo
                .getInventoryRate() + mInfo.getCashFlow() + mInfo.getReturnRate() + mInfo.getRewardRate() + mInfo
                .getPbv() + mInfo.getPeg();
        tvSource.setText(mScore + "");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            isFinsh();
            return false;
        }
        else
        {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void isFinsh()
    {
        HintHelper.IDialogAction action = new HintHelper.IDialogAction()
        {
            @Override
            public void action(int which, Object object)
            {
                AddActivity.this.finish();
            }
        };
        HintHelper.getInstance(AddActivity.this).askDialog("是否返回？", action, null);
    }
}
