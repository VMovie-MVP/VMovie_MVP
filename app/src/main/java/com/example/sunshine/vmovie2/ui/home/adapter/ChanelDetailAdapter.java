package com.example.sunshine.vmovie2.ui.home.adapter;

import android.content.Context;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.ChanelDetailBean;

import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelDetailAdapter extends BaseLvAdapter<ChanelDetailBean> {

    private static final String TAG = ChanelDetailAdapter.class.getSimpleName();


    public ChanelDetailAdapter(List data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, ChanelDetailBean item, int position) {
        holder.setText(R.id.chanel_detail_lv_item1_title, item.getTitle());
        holder.setText(R.id.chanel_detail_lv_item1_sub,item.getCates().get(0).getCatename()+"  /  "+setDuration(item.getDuration()));
        holder.setImage(R.id.chanel_detail_lv_item1_image,item.getImage());
    }

    public String setDuration(String s) {
        int d = Integer.valueOf(s);
        int minute = 0;
        int second = 0;
        String duration = "";
        if (d / 60 > 0) {
            minute = d / 60;
            second = d - minute * 60;
            if (second <=9){
                return duration = minute + "′0" + second + "″";
            }
            return duration = minute + "′" + second + "″";
        } else {
            if (d > 9) {
                second = d;
                return duration = second + "″";
            } else if (d <= 9){
                second = d;
                return duration = "0" + second + "″";
            }
        }
        return 0+"'"+00+"''";
    }


}
