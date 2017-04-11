package com.example.sunshine.vmovie2.ui.home.behind.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sunshine.vmovie2.ui.home.behind.BehindPageFragment;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindTabAdapter extends FragmentPagerAdapter {

    private List<BehindTitleBean.DataBean> data;
    private List<BehindPageFragment> pageData;

    public BehindTabAdapter(FragmentManager fm, List<BehindTitleBean.DataBean> data) {
        super(fm);
        if (data != null) {
            this.data = data;
        }else {
            this.data = new ArrayList<>();
        }
        pageData = new ArrayList<>();

    }

    public void updataRes(List<BehindTitleBean.DataBean> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);

            for (int i = 0; i < data.size(); i++) {
                BehindPageFragment behindPageFragment = new BehindPageFragment();
                pageData.add(behindPageFragment);
            }
            notifyDataSetChanged();
        }
    }
    @Override
    public BehindPageFragment getItem(int position) {
        return pageData.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getCatename();
    }
}
