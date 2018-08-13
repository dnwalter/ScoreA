package com.ousy.scorea.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ousy.scorea.R;
import com.ousy.scorea.models.SharesInfo;

import java.util.List;

/**
 * Created by ousyy on 2018/6/24.
 */

public class ScoreAdapter extends BaseRecyclerViewAdapter<SharesInfo> implements View.OnLongClickListener
{
    private Holder mHolder;
    public ScoreAdapter(Context context, List<SharesInfo> list)
    {
        super(context, list);
    }

    @Override
    public View createView(ViewGroup viewGroup, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item_source,viewGroup,false);

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
        mHolder.tvName.setText(sharesInfo.getName());
        mHolder.tvNum.setText(sharesInfo.getNum());
        mHolder.tvDate.setText(sharesInfo.getDate());
        mHolder.tvScore.setText(sharesInfo.getScore()+"");
        mHolder.llytRoot.setTag(position);
        mHolder.llytRoot.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v)
    {
        if (null != mCallback)
        {
            mCallback.onItemViewClick(v.getId(), (Integer) v.getTag());
        }
        return false;
    }

    private class Holder extends RecyclerView.ViewHolder
    {
        private LinearLayout llytRoot;
        private View view1;
        private View view2;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvNum;
        private TextView tvScore;

        public Holder(View itemView)
        {
            super(itemView);
            llytRoot = (LinearLayout) itemView.findViewById(R.id.item_llyt_root);
            view1 = itemView.findViewById(R.id.item_name);
            view2 = itemView.findViewById(R.id.item_num);
            tvName = (TextView) view1.findViewById(R.id.item_left);
            tvDate = (TextView) view1.findViewById(R.id.item_right);
            tvNum = (TextView) view2.findViewById(R.id.item_left);
            tvScore = (TextView) view2.findViewById(R.id.item_right);
        }
    }
}
