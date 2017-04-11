package com.example.sunshine.vmovie2.bean;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
public class CatesBean {
    private int cateid;
    private String catename;

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    @Override
    public String toString() {
        return "CatesModel{" +
                "cateid=" + cateid +
                ", catename='" + catename + '\'' +
                '}';
    }
}
