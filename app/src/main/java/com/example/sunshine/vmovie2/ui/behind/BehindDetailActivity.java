package com.example.sunshine.vmovie2.ui.behind;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.ui.LoginActivity;
import com.example.sunshine.vmovie2.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sunshine on 2017/4/11.
 */
public class BehindDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = BehindDetailActivity.class.getSimpleName();
    @BindView(R2.id.behind_detail_web_view)
    WebView mWebView;
    @BindView(R2.id.behind_detail_top_back)
    ImageView topBack;
    @BindView(R2.id.behind_detail_top_share)
    ImageView topShare;
    @BindView(R2.id.behind_detail_bottom_like)
    TextView likeView;
    @BindView(R2.id.behind_detail_bottom_share)
    TextView shareView;
    @BindView(R2.id.behind_detail_bottom_comment)
    TextView commentView;
    @BindView(R2.id.behind_detail_bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R2.id.behind_detail_title)
    TextView title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_behind_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String requestUrl = intent.getStringExtra("request_url");
        String likeNum = intent.getStringExtra("like_num");
        String shareNum = intent.getStringExtra("share_num");
        String header = intent.getStringExtra("header");

        if (header != null) {
            bottomLayout.setVisibility(View.GONE);
//            Log.e(TAG, "initView: ======" + requestUrl);
            title.setText("标题");
        }
        if (requestUrl.equals("")) {
            TextView emptyText = (TextView) findViewById(R.id.behind_detail_web_not_find);
            emptyText.setVisibility(View.VISIBLE);
        } else {
            //加载需要显示的网页
            mWebView.loadUrl(requestUrl);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            //设置Web视图
            mWebView.setWebViewClient(new HelloWebViewClient());
        }

        likeView.setText(likeNum);
        shareView.setText(shareNum);
    }

    @OnClick(value = {R2.id.behind_detail_top_back, R2.id.behind_detail_top_share, R2.id.behind_detail_bottom_like, R2.id.behind_detail_bottom_share, R2.id.behind_detail_bottom_comment})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.behind_detail_top_back:
                finish();
                break;
            case R.id.behind_detail_bottom_share:
            case R.id.behind_detail_top_share:
                //分享
                ShareUtil.showShare(this, "分享", null, null, null, null, null, null);
                break;
            case R.id.behind_detail_bottom_like:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.behind_detail_bottom_comment:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Log.e(TAG, "shouldOverrideUrlLoading: " + url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
