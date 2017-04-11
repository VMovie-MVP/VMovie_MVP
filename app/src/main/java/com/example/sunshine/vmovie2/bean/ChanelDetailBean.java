package com.example.sunshine.vmovie2.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class ChanelDetailBean {
    private String postid;
    private String wx_small_app_title;
    private String app_fu_title;
    private String title;
    private String image;
    private String rating;
    private String duration;
    private String publish_time;
    private String like_num;
    private String share_num;
    private List<CatesBean> cates;
    private String request_url;

    public String getWx_small_app_title() {
        return wx_small_app_title;
    }

    public void setWx_small_app_title(String wx_small_app_title) {
        this.wx_small_app_title = wx_small_app_title;
    }

    public String getApp_fu_title() {
        return app_fu_title;
    }

    public void setApp_fu_title(String app_fu_title) {
        this.app_fu_title = app_fu_title;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getShare_num() {
        return share_num;
    }

    public void setShare_num(String share_num) {
        this.share_num = share_num;
    }

    public List<CatesBean> getCates() {
        return cates;
    }

    public void setCates(List<CatesBean> cates) {
        this.cates = cates;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    @Override
    public String toString() {
        return "ChanelDetailModel{" +
                "postid='" + postid + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", like_num='" + like_num + '\'' +
                ", share_num='" + share_num + '\'' +
                ", cates=" + cates +
                ", request_url='" + request_url + '\'' +
                '}';
    }
}
