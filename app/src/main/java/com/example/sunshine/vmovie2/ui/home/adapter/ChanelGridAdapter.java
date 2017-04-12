package com.example.sunshine.vmovie2.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import com.example.mvplibrary.base.BaseLvAdapter;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.bean.ChanelBean;
import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelGridAdapter extends BaseLvAdapter<ChanelBean> {

    private static final String TAG = ChanelGridAdapter.class.getSimpleName();

    public ChanelGridAdapter(List<ChanelBean> data, Context context, String viewType, int... layoutIds) {
        super(data, context, viewType, layoutIds);
    }

    @Override
    protected void bindData(ViewHolder holder, ChanelBean item, int position) {
        Log.e(TAG, "bindData: "+item.getCatename());
        holder.setText(R.id.chanel_gv_title,String.format("#%s#",item.getCatename()));
        holder.setImage(R.id.chanel_gv_image,item.getIcon());
    }
}


