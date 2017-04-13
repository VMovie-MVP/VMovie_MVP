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
import com.example.sunshine.vmovie2.ui.LoginActivity;
import com.example.sunshine.vmovie2.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

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
    //    @BindView(R2.id.series_detail_video)
//    VideoView mVideoView;
    @BindView(R2.id.series_detail_bottom_share)
    TextView bottomShare;
    @BindView(R2.id.series_detail_top_share)
    ImageView topShare;
    @BindView(R2.id.series_detail_bottom_comment)
    TextView commentView;
    @BindView(R2.id.series_detail_bottom_cache)
    TextView cacheView;

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
//        mVideoView.setMediaController(new MediaController(this));
//        mVideoView.setVideoURI(Uri.parse(HttpParams.VIDEO_VIEW_URL));
//        mVideoView.start();
    }

    @OnClick(value = {R2.id.series_detail_top_back, R2.id.series_detail_bottom_share, R2.id.series_detail_top_share, R2.id.series_detail_bottom_comment, R2.id.series_detail_bottom_cache})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.series_detail_top_back:
                finish();
                break;
            case R.id.series_detail_bottom_share:
            case R.id.series_detail_top_share:
                ShareUtil.showShare(this, "分享", null, null, null, null, null, null);
                break;
            case R.id.series_detail_bottom_comment:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.series_detail_bottom_cache:
                startActivity(new Intent(this, LoginActivity.class));
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
