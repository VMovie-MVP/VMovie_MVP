package com.example.sunshine.vmovie2.bean;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class ChanelBean {
    private String cate_type;
    private int orderid;
    private String cateid;
    private String catename;
    private String alias;
    private String icon;

    public String getCate_type() {
        return cate_type;
    }

    public void setCate_type(String cate_type) {
        this.cate_type = cate_type;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "ChanelModel{" +
                "cate_type=" + cate_type +
                ", orderid=" + orderid +
                ", cateid=" + cateid +
                ", catename='" + catename + '\'' +
                ", alias='" + alias + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
