package com.example.sunshine.vmovie2;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.utils.SpfUtil;

import butterknife.BindView;


public class SplashActivity extends BaseActivity implements Animation.AnimationListener {
    @BindView(R2.id.splash_image)
    ImageView mImage;

    private ImageView mSplashBackGround;
    private boolean isGuideShow;

    @Override
    protected void doBeforeLayout() {
        super.doBeforeLayout();
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.05f, 1, 1.05f, 0.5f, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        mImage.startAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(this);
    }




    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isGuideShow= SpfUtil.getBoolean(getApplicationContext(),"isGuideShow",false);
        if (isGuideShow){
            jump(MainActivity.class);
        }else {
            jump(GuideActivity.class);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void jump(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

}
