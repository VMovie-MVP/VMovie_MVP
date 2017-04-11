package com.example.sunshine.vmovie2.ui.series.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
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
public class SeriesDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R2.id.series_detail_web_view)
    WebView mWebView;
    @BindView(R2.id.series_detail_top_back)
    ImageView topBack;
    @BindView(R2.id.series_detail_web_not_find)
    TextView emptyText;
    @BindView(R2.id.series_detail_video)
    VideoView mVideoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_series;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String seriesId = intent.getStringExtra("seriesId");
        topBack.setOnClickListener(this);

        if (seriesId.equals("")) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            //加载需要显示的网页
            mWebView.loadUrl(HttpParams.Series_Detail_URL1 + seriesId + HttpParams.Series_Detail_URL2);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            //设置Web视图
            mWebView.setWebViewClient(new HelloWebViewClient());
        }
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(Uri.parse(HttpParams.VIDEO_VIEW_URL));
        mVideoView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.series_detail_top_back:
                finish();
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