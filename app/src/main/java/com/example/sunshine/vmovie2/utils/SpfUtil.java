package com.example.sunshine.vmovie2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class SpfUtil {
    private static final String SHARE_PREFS_NAME = "rdxc";

    public static boolean IS_LOGIN = false;

    public static void putBoolean(Context ctx, String key, boolean value) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getBoolean(key, defaultValue);
    }


    public static void putString(Context ctx, String key, String value) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getString(key, defaultValue);
    }

    public static void putInt(Context ctx, String key, int value) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        pref.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
                Context.MODE_PRIVATE);

        return pref.getInt(key, defaultValue);
    }


    /**
     * 是否第一次启动
     */
    public static final String FIRST_START = "first_start";

    /**
     * 记录上一次的用户状态
     */
    public static final String FOSTER_STATE = "foster_state";
    /**
     * 记录数据库表是否被创建
     */
    public static final String TABLE_IS_CREATE = "table_is_create";
    /**
     * 是否仅仅在WIFI下显示图片
     */
    public static final String IS_WIFI_LOAD_IMAGE = "is_wifi_load_image";

    /**
     * @描述 :是否仅仅在WIFI下显示图片参数
     */
    public static final class isWifiLoadImage {
        public static final String IS_WIFI_LOAD = "is_wifi_load";
    }

    /**
     * @描述 :第一次启动需要参数
     */
    public static final class FirstStart {
        public static final String IS_START = "is_start";
    }

    /**
     * 储存用户上次登录状态
     */
    public static final class FosterState {
        public static final String FOSTERSTATE = "foster_state";
    }

    /**
     * 判断是否创建表
     */
    public static final class TableIsCreate {
        public static final String IS_CREATE = "is_create";
    }


}
