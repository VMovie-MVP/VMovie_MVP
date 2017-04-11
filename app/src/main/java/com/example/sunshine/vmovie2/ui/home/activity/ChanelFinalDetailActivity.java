package com.example.sunshine.vmovie2.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.constant.HttpParams;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/11.
 */
public class ChanelFinalDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.chanel_final_detail_web_view)
    WebView mWebView;
    @BindView(R2.id.chanel_final_detail_bottom_like)
    TextView likeView;
    @BindView(R2.id.chanel_final_detail_bottom_share)
    TextView shareView;
    @BindView(R2.id.chanel_final_detail_video)
    VideoView mVideoView;



    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_final_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        findViewById(R.id.chanel_final_detail_top_back).setOnClickListener(this);
        Intent intent=getIntent();
        String likeNum = intent.getStringExtra("like_num");
        String shareNum = intent.getStringExtra("share_num");
        String requestUrl = intent.getStringExtra("request_url");
        if (requestUrl.equals("")){
            TextView emptyText = (TextView) findViewById(R.id.chanel_final_detail_web_not_find);
            emptyText.setVisibility(View.VISIBLE);
        }else {
            //加载需要显示的网页
            mWebView.loadUrl(requestUrl);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            //设置Web视图
            mWebView.setWebViewClient(new HelloWebViewClient());
        }
        likeView.setText(likeNum);
        likeView.setOnClickListener(this);
        shareView.setText(shareNum);
        shareView.setOnClickListener(this);

        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(Uri.parse(HttpParams.VIDEO_VIEW_URL));
        mVideoView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chanel_final_detail_top_back:
                finish();
                break;
            case R.id.chanel_final_detail_bottom_like:
//                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.chanel_final_detail_bottom_share:
//                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
