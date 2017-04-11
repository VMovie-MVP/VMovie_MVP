package com.example.sunshine.vmovie2.ui.home.behind.adapter;

import android.content.Context;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindBean;

import java.util.List;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindPageAdapter extends BaseLvAdapter<BehindBean> {
    public BehindPageAdapter(List<BehindBean> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, BehindBean item, int position) {

    }
}
