package com.example.sunshine.vmovie2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.MainActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by sunshine on 2017/4/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {


    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R2.id.login_email)
    EditText emailEdit;
    @BindView(R2.id.guide_pass_word)
    EditText passwordEdit;
    @BindView(R2.id.login_btn)
    Button loginBtn;
    @BindView(R2.id.login_back)
    ImageView topBack;
    @BindView(R2.id.login_vblog)
    ImageView vbLogin;
    @BindView(R2.id.login_qq)
    ImageView qqLogin;
    @BindView(R2.id.login_wechat)
    ImageView wxLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        emailEdit.setOnEditorActionListener(this);
        passwordEdit.setOnEditorActionListener(this);
    }

    @OnClick(value = {R2.id.login_back,R2.id.login_btn,R2.id.login_vblog, R2.id.login_qq,R2.id.login_wechat})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                LoginActivity.this.finish();
                break;
            case R.id.login_btn:
                login();
                break;
            case R.id.login_vblog:
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_qq:
                loginQQ();
                break;
            case R.id.login_wechat:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void login() {
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (email.equals("test")&&password.equals("8023")){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            SharedPreferences sp = getSharedPreferences("isFirstLogin", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isLogin", true);
            editor.commit();

            Intent intent=getIntent();
            intent.putExtra("photo", "http://image.so.com/v?q=头像&src=relation_qqimage&fromurl=http%3A%2F%2Fwww.duitang.com%2Fpeople%2Fmblog%2F122735416%2Fdetail%2F#q=%E5%A4%B4%E5%83%8F&src=relation_qqimage&fromurl=http%3A%2F%2Fwww.duitang.com%2Fpeople%2Fmblog%2F122735416%2Fdetail%2F&lightboxindex=2&id=ccedb8303d243ef875eaf27e432a1b54&multiple=0&itemindex=0&dataindex=2");
            intent.putExtra("name", "sunshine");
            setResult(200, intent);
//            startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    private void loginQQ() {
        /**
         * 登录流程
         *  ①获取指定平台
         *  ②（判断是否授权，）添加平台监听
         *  ③调用第三方登录
         *  ④处理用户数据
         */
        //授权
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb db = platform.getDb();
                Log.e(TAG, "onComplete" + db.toString());
                String userIcon = db.getUserIcon();
                String userName = db.getUserName();
                Log.e(TAG, "onComplete: userIcon-->" + userIcon + "-- userName-->" + userName);

//                Set<String> keys= hashMap.keySet();
//                for (String key : keys) {
//                    Log.e(TAG, "onComplete: "+key+":"+hashMap.get(key));
//                }
//
////                String qqPhotoUrl = (String) hashMap.get("figureurl_qq_1");
////                String nickName = (String) hashMap.get("nickname");

                SharedPreferences sp = getSharedPreferences("isFirstLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isLogin", true);
                editor.commit();

                Intent intent = getIntent();
                intent.putExtra("photo", userIcon);
                intent.putExtra("name", userName);
                setResult(200, intent);
//                startActivity(intent);
               LoginActivity.this.finish();

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e(TAG, "onError: " + throwable.getCause() + "\t--------" + throwable.getMessage());
                LoginActivity.this.finish();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });

        //移除授权
        platform.removeAccount();
        //调用授权
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
        //显示用户
        platform.showUser(null);//授权并获取用户信息

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (v.getId()) {
            case R.id.login_email:
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    login();
                    return true;
                }
                break;
            case R.id.guide_pass_word:
                if (actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    passwordEdit.requestFocus();
                    return true;
                }
                break;
        }
        return false;

    }
}
