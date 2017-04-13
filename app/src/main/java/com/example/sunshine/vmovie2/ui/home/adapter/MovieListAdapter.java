package com.example.sunshine.vmovie2.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.MovieBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListAdapter extends BaseLvAdapter<MovieBean> {

    private Activity activity;

    public MovieListAdapter(List<MovieBean> data, Context context, String viewType,Activity activity, int... layoutIds) {
        super(data, context, viewType, layoutIds);
        this.activity=activity;
    }

    @Override
    protected void bindData(ViewHolder holder, MovieBean item, int position) {
        holder.setText(R.id.movie_list_item_title, item.getWx_small_app_title());
        holder.setImage(R.id.movie_list_item_image,item.getImage());
        holder.setText(R.id.movie_list_item_catename_and_time, item.getCates().get(0).getCatename()+"  /  "+setDuration(String.valueOf(item.getDuration())));
        holder.setText(R.id.movie_list_item_time, setDuration(String.valueOf(item.getDuration())));

        if (position>0){
            String publish_time = getItem(position - 1).getPublish_time();
            String textUp = dateParse(publish_time);
            String text = dateParse(item.getPublish_time());
            if (!textUp.equals(text)){
                TextView textView = (TextView) activity.findViewById(R.id.activity_main_home_title_movie_list);
                textView.setText(text);
                holder.setText(R.id.movie_list_item_data_tv,"-" + text + "-");
                holder.findView(R.id.movie_list_item_data_layout).setVisibility(View.VISIBLE);
            }else {
                TextView textView = (TextView) activity.findViewById(R.id.activity_main_home_title_movie_list);
                textView.setText(text);
                holder.findView(R.id.movie_list_item_data_layout).setVisibility(View.GONE);
            }


        }
    }

    private String dateParse(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM.d", Locale.ENGLISH);
        String parseDate = dateFormat.format(new Date(Long.parseLong(time) * 1000));
        return parseDate.equals(dateFormat.format(new Date())) ? "最新" : parseDate;
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
