package com.example.sunshine.vmovie2.bean;

import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListHeadData {
    private String status;
    private String msg;
    private List<MovieListHeadBean1> data;

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

    public List<MovieListHeadBean1> getData() {
        return data;
    }

    public void setData(List<MovieListHeadBean1> data) {
        this.data = data;
    }
}
