package com.example.sunshine.vmovie2.ui.behind;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.ui.behind.adapter.BehindTabAdapter;
import com.example.sunshine.vmovie2.ui.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.behind.contract.BehindTitleContract;
import com.example.sunshine.vmovie2.ui.behind.model.BehindTitleModel;
import com.example.sunshine.vmovie2.ui.behind.presenter.BehindPresenter;

import butterknife.BindView;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindFragment extends BaseFragment<BehindPresenter, BehindTitleModel> implements BehindTitleContract.BehindTitleView, ViewPager.OnPageChangeListener {
    public static final String TAG = BehindFragment.class.getName();

    @BindView(R2.id.behind_tab)
    TabLayout tabLayout;
    @BindView(R2.id.behind_viewpager)
    ViewPager viewPager;
    private BehindTabAdapter tabAdapter;
    private BehindTitleBean behindTitleBean;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_behind;
    }

    @Override
    public void initView() {
        tabAdapter = new BehindTabAdapter(getActivity().getSupportFragmentManager(), null);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
        mPresenter.getTitle();
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

    }

    @Override
    public void returnBehindTitleBean(BehindTitleBean behindTitleBean) {
        if (behindTitleBean.getMsg().equals("OK")&&behindTitleBean.getStatus().equals("0")) {
            tabAdapter.updataRes(behindTitleBean.getData());
            this.behindTitleBean = behindTitleBean;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BehindPageFragment pageFragment = tabAdapter.getItem(position);
        pageFragment.setCateId(behindTitleBean.getData().get(position).getCateid());
        pageFragment.setPage(1);
        pageFragment.mPresenter.getBehindBean(behindTitleBean.getData().get(position).getCateid(),1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
