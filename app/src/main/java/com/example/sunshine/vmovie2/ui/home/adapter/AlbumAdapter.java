package com.example.sunshine.vmovie2.ui.home.adapter;

import android.content.Context;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.AlbumBean;
import com.example.sunshine.vmovie2.bean.ChanelDetailBean;

import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class AlbumAdapter extends BaseLvAdapter<ChanelDetailBean> {

    public AlbumAdapter(List<ChanelDetailBean> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, ChanelDetailBean item, int position) {
        holder.setText(R.id.chanel_detail_lv_item2_album_title, item.getTitle());
        holder.setText(R.id.chanel_detail_lv_item2_album_album_name, "专题");
        holder.setText(R.id.chanel_detail_lv_item2_album_sub, item.getApp_fu_title());
        holder.setImage(R.id.chanel_detail_lv_item2_album_image, item.getImage());
    }
}
