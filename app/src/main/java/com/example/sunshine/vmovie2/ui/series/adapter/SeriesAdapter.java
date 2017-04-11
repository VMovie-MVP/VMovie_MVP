package com.example.sunshine.vmovie2.ui.series.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.SeriesData;
import com.example.sunshine.vmovie2.ui.series.activity.SeriesDetailActivity;

import java.util.List;

import static com.example.sunshine.vmovie2.ui.series.SeriesFragment.TAG;

/**
 * Created by sunshine on 2017/4/11.
 */

public class SeriesAdapter extends BaseLvAdapter<SeriesData.SeriesBeans> {

    private Context context;
    public SeriesAdapter(List<SeriesData.SeriesBeans> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
        this.context=context;
    }

    @Override
    protected void bindData(ViewHolder holder, final SeriesData.SeriesBeans item, int position) {
        holder.setText(R.id.series_lv_item_title, item.getTitle());
        holder.setText(R.id.series_lv_item_update, "已更新至" + item.getUpdate_to() + "集");
        holder.setText(R.id.series_lv_item_follower_num, item.getFollower_num() + "人已订阅");
        holder.setText(R.id.series_lv_item_content, item.getContent());
        holder.setImage(R.id.series_lv_item_image, item.getImage());
        final ImageView imageView = (ImageView) holder.findView(R.id.series_lv_item_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, SeriesDetailActivity.class);
                intent.putExtra("seriesId",item.getSeriesid());
                Log.e(TAG, "onClick: seriesId--->"+ item.getSeriesid());
                context.startActivity(intent);
            }
        });
    }
}
