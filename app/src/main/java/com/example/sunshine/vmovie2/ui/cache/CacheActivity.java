package com.example.sunshine.vmovie2.ui.cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.utils.SpfUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CacheActivity extends BaseActivity {
    @BindView(R2.id.cache_activity_no_cache)
    TextView noCache;
    @BindView(R2.id.cache_activity_top_back)
    ImageView topBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cache;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if (SpfUtil.NO_CACHE) {
            noCache.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(value = {R2.id.cache_activity_top_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cache_activity_top_back:
                finish();
                break;
        }
    }
}
