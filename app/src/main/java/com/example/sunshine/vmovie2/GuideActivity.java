package com.example.sunshine.vmovie2;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.ui.main.GuideAdapter;
import com.example.sunshine.vmovie2.utils.SpfUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/12.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener {

    private static final String TAG = GuideActivity.class.getSimpleName();
    @BindView(R2.id.guide_container_vp)
    ViewPager viewPager;
    @BindView(R2.id.ll_container)
    LinearLayout llContainer;
    @BindView(R2.id.guide_text1)
    ImageView guideText1;
    @BindView(R2.id.guide_text2)
    ImageView guideText2;
    @BindView(R2.id.guide_tex3)
    ImageView guideText3;
    @BindView(R2.id.btn_start)
    Button startBtn;
    @BindView(R2.id.guide_version)
    ImageView guideVersion;
    @BindView(R2.id.guide_point)
    ImageView guidePoint;

    private List<ImageView> imageViewData = new ArrayList<>();
    private int[] imageIds = new int[]{R.mipmap.guide_bg1, R.mipmap.guide_bg2, R.mipmap.guide_bg3};
    private int mPointDis;//原点移动距离
    private GuideAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        startBtn.setOnClickListener(this);
        initData();
        adapter = new GuideAdapter(imageViewData, imageIds);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        guidePoint.getViewTreeObserver().addOnGlobalLayoutListener(this);

    }



    private void initData() {
        ImageView image = null;
        for (int i = 0; i < imageIds.length; i++) {
            image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageViewData.add(image);
            //初始化小圆点
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shap_point_nomal);
            //初始化布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            llContainer.addView(point);
        }
    }

    //-------------- viewPager 监听回调函数--------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, "onPageScrolled: 当前位置-->" + position + "===偏移百分比--》" + positionOffset);
        int leftMargin = (int) (mPointDis * positionOffset + position * mPointDis + 0.5f);
        //获取小红点的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) guidePoint.getLayoutParams();
        params.leftMargin = leftMargin;
        guidePoint.setLayoutParams(params);

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            guideText1.setVisibility(View.VISIBLE);
        } else {
            guideText1.setVisibility(View.GONE);
        }
        if (position == 1) {
            guideText2.setVisibility(View.VISIBLE);
        } else {
            guideText2.setVisibility(View.GONE);
        }
        if (position == imageIds.length - 1) {
            guideText3.setVisibility(View.VISIBLE);
            startBtn.setVisibility(View.VISIBLE);
            guideVersion.setVisibility(View.VISIBLE);
        } else {
            guideText3.setVisibility(View.GONE);
            startBtn.setVisibility(View.GONE);
            guideVersion.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onGlobalLayout() {
        mPointDis = llContainer.getChildAt(1).getLeft()
                - llContainer.getChildAt(0).getLeft();
        Log.e(TAG, "onGlobalLayout: mPointDis--->" + mPointDis);

        //移除观察者
        guidePoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);


    }

    //----------------------开始体验按钮点击事件监听回调函数- --------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                SpfUtil.putBoolean(getApplicationContext(), "isGuideShow", true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }
    }


}
