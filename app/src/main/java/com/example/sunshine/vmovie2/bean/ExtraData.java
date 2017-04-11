package com.example.sunshine.vmovie2.bean;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class ExtraData {
    private String app_banner_type;
    private String app_banner_param;

    public String getApp_banner_type() {
        return app_banner_type;
    }

    public void setApp_banner_type(String app_banner_type) {
        this.app_banner_type = app_banner_type;
    }

    public String getApp_banner_param() {
        return app_banner_param;
    }

    public void setApp_banner_param(String app_banner_param) {
        this.app_banner_param = app_banner_param;
    }

    @Override
    public String toString() {
        return "ExtraData{" +
                "app_banner_type='" + app_banner_type + '\'' +
                ", app_banner_param='" + app_banner_param + '\'' +
                '}';
    }
}
