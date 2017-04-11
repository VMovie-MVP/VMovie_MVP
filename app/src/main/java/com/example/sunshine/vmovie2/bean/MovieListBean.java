package com.example.sunshine.vmovie2.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class MovieListBean {
    private String status;
    private String msg;


    List<MovieBean> data;

    public List<MovieBean> getData() {
        return data;
    }

    public void setData(List<MovieBean> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MovieListModel{" +
                "data=" + data +
                '}';
    }
}
