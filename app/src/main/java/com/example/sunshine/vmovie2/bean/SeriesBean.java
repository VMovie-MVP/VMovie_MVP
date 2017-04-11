package com.example.sunshine.vmovie2.bean;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class SeriesBean {
    private String seriesid;
    private String title;
    private String image;
    private String weekly;
    private String content;
    private String app_image;
    private int isfollow;
    private int is_end;
    private int update_to;
    private int follower_num;

    public String getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(String seriesid) {
        this.seriesid = seriesid;
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

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApp_image() {
        return app_image;
    }

    public void setApp_image(String app_image) {
        this.app_image = app_image;
    }

    public int getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(int isfollow) {
        this.isfollow = isfollow;
    }

    public int getIs_end() {
        return is_end;
    }

    public void setIs_end(int is_end) {
        this.is_end = is_end;
    }

    public int getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(int update_to) {
        this.update_to = update_to;
    }

    public int getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(int follower_num) {
        this.follower_num = follower_num;
    }

    @Override
    public String toString() {
        return "SeriesModel{" +
                "seriesid=" + seriesid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", weekly='" + weekly + '\'' +
                ", content='" + content + '\'' +
                ", app_image='" + app_image + '\'' +
                ", isfollow=" + isfollow +
                ", is_end=" + is_end +
                ", update_to=" + update_to +
                ", follower_num=" + follower_num +
                '}';
    }
}
