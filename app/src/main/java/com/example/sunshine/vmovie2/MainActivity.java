package com.example.sunshine.vmovie2;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sunshine.vmovie2.ui.LoginActivity;
import com.example.sunshine.vmovie2.ui.home.HomeFragment;
import com.example.sunshine.vmovie2.ui.behind.BehindFragment;
import com.example.sunshine.vmovie2.ui.series.SeriesFragment;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Fragment mShowFragment;//正在显示的Fragment
    private boolean isExit;//双击退出的标志位
    @BindView(R2.id.activity_main_cover)
    View mCover;
    @BindView(R2.id.setting_imageView2)
    ImageView settingImage;
    @BindView(R2.id.activity_main_cover_click_to_login)
    ImageView mLogin;
    @BindView(R2.id.message_imageView3)
    ImageView msgImage;
    @BindView(R2.id.main_cover_my_cache)
    TextView myCache;
    @BindView(R2.id.main_cover_my_like)
    TextView myLike;

    @BindView(R2.id.activity_main_open_side)
    ImageView openCover;
    @BindView(R2.id.activity_main_cover_close)
    ImageView mCloseCover;
    @BindView(R2.id.activity_main_cover_rg)
    RadioGroup mRg;
    @BindView(R2.id.activity_main_cover_rg_home)
    RadioButton mRgHome;
    @BindView(R2.id.activity_main_cover_rg_series)
    RadioButton mRgSeries;
    @BindView(R2.id.activity_main_cover_rg_behind)
    RadioButton mRgBehind;
    @BindView(R2.id.activity_main_title)
    TextView mTitle;
    @BindView(R2.id.activity_main_home_title)
    View mHomeTitle;
    @BindView(R2.id.activity_main_home_indicator)
    View mIndicator;
    @BindView(R2.id.activity_main_search)
    ImageView mSearch;
    @BindView(R2.id.activity_main_home_title_movie_list)
    TextView mMovieListTitle;
    @BindView(R2.id.activity_main_home_title_channel_list)
    TextView mChannelListTitle;
    @BindView(R2.id.activity_main_cover_click_to_login_text)
    TextView mLoginText;

    private OnTitleClickListener onTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRg.setOnCheckedChangeListener(this);
        //设置第一个为选中
        mRgHome.setChecked(true);

    }

    @OnClick(value = {R2.id.setting_imageView2, R2.id.activity_main_cover_click_to_login,
            R2.id.message_imageView3, R2.id.main_cover_my_cache, R2.id.main_cover_my_like,
            R2.id.activity_main_open_side, R2.id.activity_main_cover_close,
            R2.id.activity_main_cover_rg_home, R2.id.activity_main_cover_rg_series,
            R2.id.activity_main_cover_rg_behind, R2.id.activity_main_search,
            R2.id.activity_main_home_title_movie_list, R2.id.activity_main_home_title_channel_list})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_imageView2:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_cover_click_to_login:
                startActivityForResult(new Intent(this, LoginActivity.class), 100);
                break;
            case R.id.message_imageView3:
                Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_cover_my_cache:
                Toast.makeText(this, "我的缓存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_cover_my_like:
                Toast.makeText(this, "我喜欢的", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_open_side:
                showCover();
                break;
            case R.id.activity_main_cover_close:
                closeCover();
                break;
            case R.id.activity_main_cover_rg_home:
            case R.id.activity_main_cover_rg_series:
            case R.id.activity_main_cover_rg_behind:
                //如果Cover正在显示，则隐藏Cover
                hideCover();
                break;
            case R.id.activity_main_search:
                // TODO: 2017/3/6 跳转搜索页面
                break;
            case R.id.activity_main_home_title_movie_list:
                //点击的时候，触发接口中的方法
                if (onTitleClickListener != null) {
                    onTitleClickListener.onMovieTitleClick();
                    TextView check = (TextView) findViewById(R.id.activity_main_home_title_channel_list);
                    TextView unCheck = (TextView) findViewById(R.id.activity_main_home_title_movie_list);
                    check.setTextColor(getResources().getColor(R.color.colorGray6));
                    unCheck.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                break;
            case R.id.activity_main_home_title_channel_list:
                if (onTitleClickListener != null) {
                    onTitleClickListener.onChannelTitleClick();
                    TextView check = (TextView) findViewById(R.id.activity_main_home_title_movie_list);
                    TextView unCheck = (TextView) findViewById(R.id.activity_main_home_title_channel_list);
                    check.setTextColor(getResources().getColor(R.color.colorGray6));
                    unCheck.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_main_cover_rg_home:
                switchPage(HomeFragment.TAG);
                mTitle.setVisibility(View.GONE);
                mHomeTitle.setVisibility(View.VISIBLE);
                break;
            case R.id.activity_main_cover_rg_series:
                switchPage(SeriesFragment.TAG);
                mHomeTitle.setVisibility(View.GONE);
                mTitle.setVisibility(View.VISIBLE);
                mTitle.setText("系列");
                break;
            case R.id.activity_main_cover_rg_behind:
                switchPage(BehindFragment.TAG);
                mHomeTitle.setVisibility(View.GONE);
                mTitle.setVisibility(View.VISIBLE);
                mTitle.setText("幕后");
                break;
        }
        mCover.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String photoUrl = data.getStringExtra("photo");
            String name = data.getStringExtra("name");
            Log.e(TAG, "onActivityResult: photo-------->" + photoUrl);
            Log.e(TAG, "onActivityResult: name-------->" + name);
            Picasso.with(this).load(Uri.parse(photoUrl)).placeholder(R.mipmap.ic_launcher)
                    .transform(new CropCircleTransformation())
                    .into(mLogin);
            mLoginText.setText(name);
        }
    }

    private void showCover() {
        mCover.setVisibility(View.VISIBLE);

        Log.e(TAG, "showCover: " + mCover.getVisibility());
        //控制按钮的动画
        /**
         * 使用渐变动画实现，每个实现之间有一定延迟
         */
        mRgHome.setAlpha(0);
        mRgSeries.setAlpha(0);
        mRgBehind.setAlpha(0);

        ObjectAnimator home = ObjectAnimator.ofFloat(mRgHome, "alpha", 0, 1);
        home.setDuration(300);
        ObjectAnimator series = ObjectAnimator.ofFloat(mRgSeries, "alpha", 0, 1);
        series.setDuration(300);
        ObjectAnimator behind = ObjectAnimator.ofFloat(mRgBehind, "alpha", 0, 1);
        behind.setDuration(300);
        AnimatorSet rgSet = new AnimatorSet();
        rgSet.play(series).after(home).before(behind);
        rgSet.start();

        //关闭按钮的动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mCloseCover, "scaleX", 0, 1.2f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mCloseCover, "scaleY", 0, 1.2f, 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }

    private void closeCover() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mCloseCover, "scaleX", 1, 1.2f, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mCloseCover, "scaleY", 1, 1.2f, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mCover.setVisibility(View.GONE);

                Log.e(TAG, "onAnimationEnd: " + mCover.getVisibility());
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();

    }

    private void hideCover() {
        if (mCover.getVisibility() == View.VISIBLE) {
            mCover.setVisibility(View.GONE);
        }
    }



    /**
     * 页面切换控制
     *
     * @param tag 通过tag动态加载Fragment
     */
    public void switchPage(String tag) {
        /**
         * ①隐藏正在像是的页面
         * ②显示要显示的页面
         *      1.去缓存中查找相应的Fragment并显示
         *      2.若没有找到则重新实例化，添加到缓存中，然后显示
         *
         *  动态添加Fragment
         *  ①获取一个FragmentManager
         *  ②开启事务
         *  ③添加动作
         *  ④提交
         *
         */

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mShowFragment != null) {
            //隐藏当前显示的页面
            transaction.hide(mShowFragment);
        }
        mShowFragment = fragmentManager.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = (Fragment) Class.forName(tag).newInstance();
                transaction.add(R.id.activity_main_container, mShowFragment, tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         *  当菜单显示的时候，点击返回，隐藏菜单
         *  点击提示，再按一次退出
         *
         *  ①监听返回键
         *  ②
         *
         */
        if (KeyEvent.KEYCODE_BACK == keyCode) {

            if (mCover.getVisibility() == View.VISIBLE) {
                mCover.setVisibility(View.GONE);
                return true;
            }
            //判断条件
            if (!isExit) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2 * 1000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * 在Fragment中调用此方法，设置Indicator的位置
     * offset ： 0-1
     */
    public void moveTitleIndicator(float offset) {
        int width = mHomeTitle.getWidth();
        mIndicator.setTranslationX(offset * width / 2);

    }


    /**
     * 利用接口回调，将数据传回Fragment
     * ①定义接口，定义接口中的方法
     * ②在数据产生的地方持有引用，在数据产生的时候调用接口中的方法
     * ③在需要处理数据的地方，实现接口，将引用传递到数据产生的地方
     */
    public static interface OnTitleClickListener {
        void onMovieTitleClick();

        void onChannelTitleClick();

    }
}
