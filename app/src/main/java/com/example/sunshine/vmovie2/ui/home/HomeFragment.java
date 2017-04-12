package com.example.sunshine.vmovie2.ui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.MainActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.ui.home.adapter.HomeFragmentPagerAdapter;
import com.example.sunshine.vmovie2.ui.home.fragment.ChanelListFragment;
import com.example.sunshine.vmovie2.ui.home.fragment.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements  ViewPager.OnPageChangeListener, MainActivity.OnTitleClickListener {

    public static final String TAG = HomeFragment.class.getName();

    @BindView(R2.id.fragment_home_viewPager)
    ViewPager mViewPager;
    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        Toast.makeText(mMainActivity, "onPageScrolled", Toast.LENGTH_SHORT).show();
        if (mMainActivity != null) {
            mMainActivity.moveTitleIndicator(position + positionOffset);
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
