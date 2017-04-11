package com.example.sunshine.vmovie2.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sunshine.vmovie2.ui.home.fragment.MovieListHeadViewFragment;

import java.util.List;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<MovieListHeadViewFragment> data;

    public MovieListViewPagerAdapter(FragmentManager fm,List<MovieListHeadViewFragment> data) {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
