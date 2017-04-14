package com.example.sunshine.vmovie2.ui.setting;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.utils.SpfUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class SettingActivity extends BaseActivity {

    @BindView(R2.id.setting_login_msg_layout)
    LinearLayout isLoginLayout;
    @BindView(R2.id.setting_exit_login_btn)
    Button exitLogin;
    @BindView(R2.id.setting_login_photo)
    ImageView loginPhoto;
    @BindView(R2.id.setting_login_name)
    TextView loginName;
    @BindView(R2.id.setting_top_back)
    ImageView topBack;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String photoUrl = intent.getStringExtra("photo");
        String name = intent.getStringExtra("name");
        Picasso.with(this).load(Uri.parse(photoUrl)).transform(new CropCircleTransformation()).into(loginPhoto);
        loginName.setText(name);
        isLoginLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(value = {R2.id.setting_top_back,R2.id.setting_exit_login_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_top_back:
                finish();
                break;
            case R.id.setting_exit_login_btn:
                isLoginLayout.setVisibility(View.GONE);
                SpfUtil.IS_LOGIN = false;
                break;
        }
    }

}
