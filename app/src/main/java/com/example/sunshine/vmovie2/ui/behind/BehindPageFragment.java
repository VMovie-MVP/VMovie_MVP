package com.example.sunshine.vmovie2.ui.behind;

import android.widget.ListView;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.ui.behind.adapter.BehindPageAdapter;
import com.example.sunshine.vmovie2.ui.behind.bean.BehindBean;
import com.example.sunshine.vmovie2.ui.behind.contract.BehindContract;
import com.example.sunshine.vmovie2.ui.behind.model.BehindBeanModel;
import com.example.sunshine.vmovie2.ui.behind.presenter.BehindBeanPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindPageFragment extends BaseFragment<BehindBeanPresenter, BehindBeanModel> implements BehindContract.BehindBeanView, PullToRefreshBase.OnRefreshListener2 {

    private String cateId = "2";
    private int page = 1;
    @BindView(R2.id.behind_recycler)
    PullToRefreshListView refreshListView;
    private ListView listView;
    private BehindBean behindBean;
    private BehindPageAdapter behindPageAdapter;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_behind_pager;
    }

    @Override
    public void initView() {
        refreshListView.setOnRefreshListener(this);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshListView.getRefreshableView();

        behindPageAdapter = new BehindPageAdapter(null, getContext(), null, R.layout.behind_lv_item);
        listView.setAdapter(behindPageAdapter);

        mPresenter.getBehindBean(cateId, page);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void returnBehindBean(BehindBean behindBean) {

        if (behindBean.getStatus().equals("0") && behindBean.getMsg().equals("OK")) {
            if (page==1){
                behindPageAdapter.updateRes(behindBean.getData());
            }else {
                behindPageAdapter.addRes(behindBean.getData());
            }
        }
        refreshListView.onRefreshComplete();
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        refreshListView.onRefreshComplete();
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        mPresenter.getBehindBean(cateId, page);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
          page++;
        mPresenter.getBehindBean(cateId,page);
    }
}
