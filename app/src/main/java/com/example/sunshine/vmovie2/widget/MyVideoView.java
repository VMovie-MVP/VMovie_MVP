package com.example.sunshine.vmovie2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class MyVideoView extends VideoView {
    public MyVideoView(Context context) {
        this(context,null);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //  获得xml指定的宽高， 指的是固定尺寸或者match_parent，对应的测量模式 EXACTLY
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

}
