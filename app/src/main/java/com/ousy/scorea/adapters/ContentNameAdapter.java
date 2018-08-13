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

public class ContentNameAdapter extends BaseRecyclerViewAdapter<SharesInfo>
{
    private Holder mHolder;

    public ContentNameAdapter(Context context, List<SharesInfo> list)
    {
        super(context, list);
    }

    @Override
    public View createView(ViewGroup viewGroup, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item_content_name, viewGroup, false);

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
    }

    private class Holder extends RecyclerView.ViewHolder
    {
        private TextView tvName;

        public Holder(View itemView)
        {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.item_content_name);
        }
    }
}
