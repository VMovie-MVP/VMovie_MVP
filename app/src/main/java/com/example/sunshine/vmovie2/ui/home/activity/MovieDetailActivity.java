package com.example.sunshine.vmovie2.ui.home.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.constant.HttpParams;
import com.example.sunshine.vmovie2.widget.AudioController;
import com.example.sunshine.vmovie2.widget.LightnessController;
import com.example.sunshine.vmovie2.widget.MyVideoView;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieDetailActivity extends BaseActivity implements Handler.Callback, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,GestureDetector.OnGestureListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, View.OnClickListener {

    private static final int UPDATE_PROGRESS = 100;
    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    @BindView(R2.id.movie_detail_video)
    MyVideoView mVideoView;
    @BindView(R2.id.movie_detail_web_view)
    WebView mWebView;
    @BindView(R2.id.teach_video_play)
    CheckBox mPlay;
    @BindView(R2.id.teach_video_current_time)
    TextView mCurrentTime;
    @BindView(R2.id.teach_video_progress)
    SeekBar mProgress;
    @BindView(R2.id.teach_video_total_time)
    TextView mTotalTime;
    @BindView(R2.id.teach_video_full_screen)
    CheckBox mFullScreen;
    @BindView(R2.id.teach_video_container)
    FrameLayout mVideoContainer;
    @BindView(R2.id.movie_detail_bottom_like)
    TextView likeView;
    @BindView(R2.id.movie_detail_bottom_share)
    TextView shareView;

    private int mVideoHeight;
    private Handler mHandler;
    private GestureDetector mGestureDetector;
    private int mHeightPixels;
    private int mWidthPixels;
    private boolean isLandScape;

    private float x;
    private float y;

    private float mFirstX;
    private float mFirstY;
    private float mLastX;
    private float mLastY;

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_detial;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mVideoView.setVideoURI(Uri.parse(HttpParams.VIDEO_VIEW_URL));
        mHandler = new Handler(this);
        mPlay.setOnCheckedChangeListener(this);
        mProgress.setOnSeekBarChangeListener(this);
        mFullScreen.setOnCheckedChangeListener(this);
        mGestureDetector = new GestureDetector(this,this);

        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnErrorListener(this);

        mHeightPixels = getResources().getDisplayMetrics().heightPixels;
        mWidthPixels = getResources().getDisplayMetrics().widthPixels;

        findViewById(R.id.movie_detail_top_back).setOnClickListener(this);

        Intent intent = getIntent();
        String postId = intent.getStringExtra("post_id");
        String likeNum = intent.getStringExtra("like_num");
        String shareNum = intent.getStringExtra("share_num");

        String detailUrl="http://app.vmoiver.com/"+postId+"?qingapp=app_new";

        if (postId.equals("")) {
            TextView emptyText = (TextView) findViewById(R.id.movie_detail_web_not_find);
            emptyText.setVisibility(View.VISIBLE);
        }else {
            mWebView.loadUrl(detailUrl);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.setWebViewClient(new HelloWebViewClient());
        }
        likeView.setText(likeNum);
        likeView.setOnClickListener(this);
        shareView.setText(shareNum);
        shareView.setOnClickListener(this);



    }

    //-----点击监听--------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_detail_top_back:
                finish();
                break;
            case R.id.movie_detail_bottom_like:
//                startActivity(new Intent(this, LoginActivity.class));
                Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
                break;
            case R.id.movie_detail_bottom_share:
            case R.id.movie_detail_top_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case UPDATE_PROGRESS:
                // 获取当前播放进度
                int currentPosition = mVideoView.getCurrentPosition();
                CharSequence currentTime = DateFormat.format("mm:ss", currentPosition);
                mCurrentTime.setText(currentTime);

                // 设置进度条的最大值
                if (mProgress.getMax() == 100) {
                    int duration = mVideoView.getDuration();
                    mProgress.setMax(duration);
                    mTotalTime.setText(DateFormat.format("mm:ss", duration));
                }
                // 设置当前进度
                mProgress.setProgress(currentPosition);

                mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 1000);
                break;
        }


        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.teach_video_play:
                if (isChecked) {
                    // 播放
                    mVideoView.start();
                    // 发送更新进度的信号
                    mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 1000);
                } else {
                    // 暂停播放
                    mVideoView.pause();
                    // 移除更新进度的信号
                    mHandler.removeMessages(UPDATE_PROGRESS);
                }
                break;
            case R.id.teach_video_full_screen:
                if (isChecked) {
                    // 切换全屏播放
                    // 添加全屏标记
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    // 请求设置为横屏  port 是纵屏  land 横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    // 记录原View的高度
                    mVideoHeight = mVideoContainer.getLayoutParams().height;
                    // 设置高度
//                    mVideoContainer.getLayoutParams().height = getResources().getDisplayMetrics().widthPixels;
                    mVideoContainer.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;

                } else {
                    // 切换为默认播放形式
                    // 清除全屏标记
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    // 请求设置为纵屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mVideoContainer.getLayoutParams().height = mVideoHeight;
                }

                break;
        }
    }

    //--------------- 进度条监听---------------------
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mVideoView.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    //--------------- 播放ok监听----------------
    @Override
    public void onPrepared(MediaPlayer mp) {
        // 设置最大进度，设置总时长
//        mp.start();
        mPlay.setChecked(true);
    }

    //--------------------- 播放错误的 监听-----------------
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        // 弹提示 网络不好  视频加载失败
        // 返回值代表当前错误是否被处理   返回false，系统会自动弹框
        Log.e(TAG, "onError: " + what);
        return true;
    }


//--------------屏幕旋转-----------

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 屏幕进入横屏状态
            isLandScape = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 屏幕恢复纵屏状态
            isLandScape = false;
        }
    }

    //--------系统返回-------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isLandScape) {
            // 变回纵屏
            mFullScreen.setChecked(false);
        } else {
            // 纵屏直接响应返回
            super.onBackPressed();
        }
    }

    //-----------触摸事件监听----------
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //使用手势探测器时，要主动调用onTouchEvent来进行事件传入（数据源传入）
        return mGestureDetector.onTouchEvent(event) && super.onTouchEvent(event);

    }

    /**
     * 快进
     * 快进操作
     * 当前位置  +  计算出来的一个快进值
     */
    private void fastForward(float xDelta) {
        int currentPosition = mVideoView.getCurrentPosition();
        //  计算滑动快进值
        int duration = mVideoView.getDuration();
        int dx = (int) (duration * xDelta / mHeightPixels);
        // 如果变化后的值大于 总的长度 ，就设置进度为总长度，如果小于总长度 就设置为计算后的值
        int min = Math.min(currentPosition + dx, duration);

        mVideoView.seekTo(min);
    }

    /**
     * 快退
     */
    private void forward(float xDelta) {
        int currentPosition = mVideoView.getCurrentPosition();
        //  计算滑动快进值
        int duration = mVideoView.getDuration();
        int dx = (int) (duration * xDelta / mHeightPixels);
        // 如果变化后的值大于 总的长度 ，就设置进度为总长度，如果小于总长度 就设置为计算后的值
        int max = Math.max(currentPosition + dx, 0);

        mVideoView.seekTo(max);
    }

    //---------------------- 手势监听------------------------------------
    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG, "onDown: ");
        x = e.getX();
        y = e.getY();
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
        mLastX = x;
        mLastY = y;
        mFirstX = x;
        mFirstY = y;
//                break;
//        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {


        if (isLandScape) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                //横向滑动
                if (e2.getX() > e1.getX()) {
                    //快进
                    fastForward(Math.abs(distanceX));
                } else if (e2.getX() < e1.getX()) {
                    //快退
                    forward(-Math.abs(distanceX));
                }
            } else if (Math.abs(distanceX) < Math.abs(distanceY)) {
                //纵向滑动
                if (x < mHeightPixels / 2) {
                    // TODO 改变屏幕亮度
                    if (distanceY < 0) {
                        // TODO 从上向下滑动 亮度调低
                        Log.e(TAG, "onTouchEvent: 亮度调低");
                        LightnessController.turnDown(this, Math.abs(distanceY), mWidthPixels);
                    } else {
                        //TODO 亮度调高
                        Log.e(TAG, "onTouchEvent: 亮度调高");
                        LightnessController.turnUp(this, distanceY, mWidthPixels);
                    }

                } else {
                    // TODO 改变媒体音量
                    if (distanceY < 0) {
                        // TODO 从上向下滑动  音量调低
                        AudioController.turnDown(this,Math.abs(distanceY), mWidthPixels);
                        Log.e(TAG, "onTouchEvent: 音量调低");
                    } else {
                        // TODO 音量调高
                        Log.e(TAG, "onTouchEvent: 音量调高");
                        AudioController.turnUp(this, distanceY, mWidthPixels);
                    }
                }


            }


        }

        Log.e(TAG, "onScroll: e1-->x:" + e1.getX() + " y:" + e1.getY() + "-- e2-->x:" + e2.getX() + " y:" + e2.getY() + "  distanceX-->" + distanceX + "  distanceY" + distanceY);

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

   //-------------------------------------------------------------

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
