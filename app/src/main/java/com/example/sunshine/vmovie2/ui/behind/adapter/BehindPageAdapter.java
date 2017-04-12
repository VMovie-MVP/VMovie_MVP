package com.example.sunshine.vmovie2.ui.behind.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.ui.behind.bean.BehindBean;

import java.util.List;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindPageAdapter extends BaseLvAdapter<BehindBean.DataBean> {

    public BehindPageAdapter(List<BehindBean.DataBean> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, BehindBean.DataBean item, int position) {
        TextView title = (TextView) holder.getView(R.id.behind_title);
        title.setText(item.getTitle());
        TextView like = (TextView) holder.getView(R.id.behind_lv_like_num);
        like.setText(item.getLike_num());
        TextView share = (TextView) holder.getView(R.id.behind_lv_share_num);
        share.setText(item.getShare_num());
        holder.setImage(R.id.behind_image,item.getImage());
    }
}
