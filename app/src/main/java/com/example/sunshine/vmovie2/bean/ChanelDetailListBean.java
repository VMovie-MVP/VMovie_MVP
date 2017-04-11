package com.example.sunshine.vmovie2.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class ChanelDetailListBean {
    private List<ChanelDetailBean> data;

    public List<ChanelDetailBean> getData() {
        return data;
    }

    public void setData(List<ChanelDetailBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChanelDetailListModel{" +
                "data=" + data +
                '}';
    }

}
