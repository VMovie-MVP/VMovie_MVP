package com.example.sunshine.vmovie2.ui.main;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class GuideAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private int[] mImageIds;

    public GuideAdapter(List<ImageView> imageViews, int[] mImageIds) {
        this.imageViews = imageViews;
        this.mImageIds = mImageIds;
    }

    @Override
    public int getCount() {
        return imageViews!=null?imageViews.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imageViews.get(position);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
