package com.example.sunshine.vmovie2.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.widget.GridView;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.ChanelBean;
import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.ui.home.adapter.ChanelGridAdapter;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListContract;
import com.example.sunshine.vmovie2.ui.home.model.ChanelListModel;
import com.example.sunshine.vmovie2.ui.home.presenter.ChanelListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChanelListFragment extends BaseFragment<ChanelListPresenter, ChanelListModel> implements ChanelListContract.ChanelListView {
    private List<ChanelBean> data=new ArrayList<>();
    @BindView(R2.id.chanel_grid_view)
    GridView mGridView;

    private List<String> cateIds = new ArrayList<>();
    private List<String> cateNames = new ArrayList<>();
    private ChanelGridAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_chanel_list;
    }

    @Override
    public void initView() {
        adapter = new ChanelGridAdapter(null, getContext(), null, R.layout.chanel_gv_item);
        mGridView.setAdapter(adapter);
        mPresenter.getChanelList();
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void returnChanelListDataBean(ChanelListBean chanelListBean) {

        ChanelBean chanelBean = new ChanelBean();
        chanelBean.setCate_type(String.valueOf(1));
        chanelBean.setCatename("热门");
        chanelBean.setIcon("http://cs.vmoiver.com/Uploads/Activity/2016-04-26/571ed9b5d2e44.jpg");
        chanelBean.setAlias("hot");
        data.add(chanelBean);
        chanelBean = new ChanelBean();
        chanelBean.setCate_type(String.valueOf(1));
        chanelBean.setCatename("专题");
        chanelBean.setIcon("http://cs.vmoiver.com/Uploads/Activity/2016-04-27/5720601258d7f.jpg");
        data.add(chanelBean);
        data.addAll(chanelListBean.getData());

        adapter.addRes(data);
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
}
