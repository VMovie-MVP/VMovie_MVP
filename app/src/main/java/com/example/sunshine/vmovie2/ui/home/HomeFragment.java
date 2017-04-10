package com.example.sunshine.vmovie2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.MainActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.ui.home.adapter.HomeFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements  ViewPager.OnPageChangeListener, MainActivity.OnTitleClickListener {

    protected View layout;
    public static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R2.id.fragment_home_viewPager)
    ViewPager mViewPager;
    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      //  Log.e(TAG, "onAttach: =======================" );
        Toast.makeText(context, "++++++++++", Toast.LENGTH_SHORT).show();
        if (context instanceof MainActivity){
            mMainActivity= (MainActivity) context;
            mMainActivity.setOnTitleClickListener(this);
        }
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initView() {
        Log.e(TAG, "initView: getFragments-->");
//        mViewPager=(ViewPager) findViewById(R.id.fragment_home_viewPager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), getFragments());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }


    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        //添加fragment
        fragments.add(new MovieListFragment());
        fragments.add(new ChanelListFragment());
        Log.e(TAG, "getFragments: ");
        return fragments;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, "onPageScrolled: ");
        Toast.makeText(mMainActivity, "onPageScrolled", Toast.LENGTH_SHORT).show();
        if (mMainActivity != null) {
            mMainActivity.moveTitleIndicator(position + positionOffset);
        }else {
            Toast.makeText(mMainActivity, "mMainActivity为", Toast.LENGTH_SHORT).show();
                    
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //-----------------------------接口回调实现方法----------------
    @Override
    public void onMovieTitleClick() {
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onChannelTitleClick() {
        mViewPager.setCurrentItem(1);
    }


}
