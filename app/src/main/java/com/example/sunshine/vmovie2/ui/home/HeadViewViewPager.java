package com.example.sunshine.vmovie2.ui.home;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mvplibrary.base.BaseCustomView;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.MovieListHeadData;
import com.example.sunshine.vmovie2.ui.home.adapter.MovieListViewPagerAdapter;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListViewPagerContract;
import com.example.sunshine.vmovie2.ui.home.fragment.MovieListHeadViewFragment;
import com.example.sunshine.vmovie2.ui.home.model.MovieListViewPagerModel;
import com.example.sunshine.vmovie2.ui.home.presenter.MovieListViewPagerPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/11.
 */
public class HeadViewViewPager extends BaseCustomView<MovieListViewPagerPresenter, MovieListViewPagerModel> implements MovieListViewPagerContract.MovieListViewPagerView, Handler.Callback, ViewPager.OnPageChangeListener {
    private static final int SEND_POSITION = 110;
    private Activity activity;
    private FragmentManager fragmentManger;
    @BindView(R2.id.movie_list_viewpager)
    ViewPager mViewPager;
    @BindView(R2.id.movie_list_viewpager_linear_layout)
    LinearLayout mLinearLayout;

    private Handler mHandler = new Handler(this);

    private FragmentManager getFragmentManger() {
        return fragmentManger;
    }

    public void setFragmentManger(FragmentManager fragmentManger) {
        this.fragmentManger = fragmentManger;
    }

    public HeadViewViewPager(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.movie_list_view_pager;
    }

    @Override
    public void initView() {
        mPresenter.getMovieListViewPager();

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        Log.e("TAG", "onError:   ---------- " + errorInfo);
    }


    @Override
    public void returnMovieListPagerViewBean(MovieListHeadData movieListHeadData) {
        MovieListViewPagerAdapter adapter = new MovieListViewPagerAdapter(getFragmentManger(), getFragment(movieListHeadData));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        initLinearLayout(movieListHeadData.getData().size());
        mViewPager.setCurrentItem(1);
        mHandler.sendEmptyMessageDelayed(SEND_POSITION, 3000);
    }

    private View mView;
    private List<View> views = new ArrayList<>();

    private void initLinearLayout(int size) {
        for (int i = 0; i < size; i++) {
            View view = LayoutInflater.from(activity).inflate(R.layout.viewpager_bottom_view, null);
            View view1 = view.findViewById(R.id.movie_list_viewpager_linear_layout_view);
            views.add(view1);
            mLinearLayout.addView(view);
        }
        mView = views.get(0);
        mView.setBackgroundColor(Color.WHITE);
    }

    private List<MovieListHeadViewFragment> getFragment(MovieListHeadData movieListHeadData) {
        List<MovieListHeadViewFragment> data = new ArrayList<>();
        for (int i = 0; i < movieListHeadData.getData().size() + 2; i++) {
            MovieListHeadViewFragment fragment = new MovieListHeadViewFragment();
            if (i == 0) {
                fragment.setUrl(movieListHeadData.getData().get(movieListHeadData.getData().size() - 1).getImage());
            } else if (i == movieListHeadData.getData().size() + 1) {
                fragment.setUrl(movieListHeadData.getData().get(0).getImage());
            } else {
                fragment.setUrl(movieListHeadData.getData().get(i - 1).getImage());
            }
            data.add(fragment);
        }

        return data;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private int index = 1;
    private boolean isStop;
    private boolean isFirst;

    @Override
    public void onPageSelected(int position) {
        if (isFirst) {
            if (position > 0 && position <= views.size()) {
                index = position;
                //      Log.e(TAG, "onPageSelected: ----------" + position);
                views.get(position - 1).setBackgroundColor(activity.getResources().getColor(R.color.colorWhite));
                mView.setBackgroundColor(activity.getResources().getColor(R.color.colorGray6));
                mView = views.get(position - 1);
            }
        }else {
            isFirst=!isFirst;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (isStop) {
                isStop = !isStop;
                mHandler.sendEmptyMessageDelayed(SEND_POSITION, 3000);
            }
            //   Log.e("TAG", "onPageScrollStateChanged: 取消停止" );
            int position = mViewPager.getCurrentItem();
            if (position == mViewPager.getAdapter().getCount() - 1) {
                //将位置滚动到第二个元素
                mViewPager.setCurrentItem(1, false);

            } else if (position == 0) {
                //将位置滚动到倒数第二个元素
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 2, false);
            }
        }
        if(state==ViewPager.SCROLL_STATE_DRAGGING){
            mHandler.removeMessages(SEND_POSITION);
            //     Log.e("TAG", "onPageScrollStateChanged: 停止" );
            isStop=true;

        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case SEND_POSITION:
                mViewPager.setCurrentItem(index%(mViewPager.getAdapter().getCount()-1)+1);
                //    Log.e("TAG", "handleMessage: +++++++++++++"+index+viewPager.getAdapter().getCount() );
                mHandler.sendEmptyMessageDelayed(SEND_POSITION,3000);
                break;

        }
        return false;
    }
}
