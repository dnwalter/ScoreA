package com.ousy.scorea.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ousy.scorea.R;
import com.ousy.scorea.models.SharesInfo;

import java.util.List;

/**
 * Created by ousyy on 2018/7/1.
 */

public class ContentAdapter extends BaseRecyclerViewAdapter<SharesInfo>
{
    private Holder mHolder;

    public ContentAdapter(Context context, List<SharesInfo> list)
    {
        super(context, list);
    }

    @Override
    public View createView(ViewGroup viewGroup, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item_content, viewGroup, false);

        return view;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void setData(RecyclerView.ViewHolder viewHolder, int position)
    {
        mHolder = (Holder) viewHolder;
        SharesInfo sharesInfo = mList.get(position);
        mHolder.tvDate.setText(sharesInfo.getDate());
        mHolder.tvScore.setText(sharesInfo.getScore()+"");
        mHolder.tvIncome.setText(sharesInfo.getIncomeRate()+"");
        mHolder.tvProfit.setText(sharesInfo.getProfitRate()+"");
        mHolder.tvMargin.setText(sharesInfo.getMargin()+"");
        mHolder.tvCost.setText(sharesInfo.getCostRate()+"");
        mHolder.tvInventory.setText(sharesInfo.getInventoryRate()+"");
        mHolder.tvCash.setText(sharesInfo.getCashFlow()+"");
        mHolder.tvReturn.setText(sharesInfo.getReturnRate()+"");
        mHolder.tvReward.setText(sharesInfo.getRewardRate()+"");
        mHolder.tvPBV.setText(sharesInfo.getPbv()+"");
        mHolder.tvPEG.setText(sharesInfo.getPeg()+"");
    }

    private class Holder extends RecyclerView.ViewHolder
    {
        private TextView tvDate;
        private TextView tvScore;
        private TextView tvIncome;
        private TextView tvProfit;
        private TextView tvMargin;
        private TextView tvCost;
        private TextView tvInventory;
        private TextView tvCash;
        private TextView tvReturn;
        private TextView tvReward;
        private TextView tvPBV;
        private TextView tvPEG;

        public Holder(View itemView)
        {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.item_content_date);
            tvScore = (TextView) itemView.findViewById(R.id.item_content_score);
            tvIncome = (TextView) itemView.findViewById(R.id.item_content_income);
            tvProfit = (TextView) itemView.findViewById(R.id.item_content_profit);
            tvMargin = (TextView) itemView.findViewById(R.id.item_content_margin);
            tvCost = (TextView) itemView.findViewById(R.id.item_content_cost);
            tvInventory = (TextView) itemView.findViewById(R.id.item_content_inventory);
            tvCash = (TextView) itemView.findViewById(R.id.item_content_cash);
            tvReturn = (TextView) itemView.findViewById(R.id.item_content_return);
            tvReward = (TextView) itemView.findViewById(R.id.item_content_reward);
            tvPBV = (TextView) itemView.findViewById(R.id.item_content_pbv);
            tvPEG = (TextView) itemView.findViewById(R.id.item_content_peg);
        }
    }
}
