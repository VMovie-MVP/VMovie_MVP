package com.example.sunshine.vmovie2.ui.message;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.utils.SpfUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    @BindView(R2.id.msg_activity_no_msg)
    TextView noMsg;
    @BindView(R2.id.msg_activity_top_back)
    ImageView topBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if (SpfUtil.NO_MSG) {
            noMsg.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(value ={R2.id.msg_activity_top_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msg_activity_top_back:
                finish();
                break;
        }
    }
}
