package com.example.sunshine.vmovie2.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.MovieBean;

import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListAdapter extends BaseLvAdapter<MovieBean> {

    public MovieListAdapter(List<MovieBean> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, MovieBean item, int position) {
        holder.setText(R.id.movie_list_item_title, item.getWx_small_app_title());
        holder.setImage(R.id.movie_list_item_image,item.getImage());
        holder.setText(R.id.movie_list_item_catename_and_time, item.getCates().get(0).getCatename()+"  /  "+setDuration(String.valueOf(item.getDuration())));
        holder.setText(R.id.movie_list_item_time, setDuration(String.valueOf(item.getDuration())));
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
