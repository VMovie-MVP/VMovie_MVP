package com.example.sunshine.vmovie2;

import android.app.Application;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by sunshine on 2017/4/10.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initPicasso();
        ShareSDK.initSDK(this);

    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
