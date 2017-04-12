package com.example.sunshine.vmovie2.ui.series;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.SeriesData;
import com.example.sunshine.vmovie2.ui.series.adapter.SeriesAdapter;
import com.example.sunshine.vmovie2.ui.series.contract.SeriesContract;
import com.example.sunshine.vmovie2.ui.series.model.SeriesModel;
import com.example.sunshine.vmovie2.ui.series.presenter.SeriesPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/11.
 */
public class SeriesFragment extends BaseFragment<SeriesPresenter, SeriesModel> implements PullToRefreshBase.OnRefreshListener2, SeriesContract.SeriesView {
    public static final String TAG = SeriesFragment.class.getName();

    @BindView(R2.id.series_list_view)
    PullToRefreshListView mPullToRefresh;
    private ListView mListView;

    private int page = 1;
    private boolean isAdd;
    private List<String> seriesIds = new ArrayList<>();
    private List<String> shareNum = new ArrayList<>();
    private List<String> commentNum = new ArrayList<>();
    private SeriesAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_series;
    }

    @Override
    public void initView() {
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefresh.setOnRefreshListener(this);
        mListView = mPullToRefresh.getRefreshableView();
        adapter = new SeriesAdapter(null, getContext(), null, R.layout.series_lv_item);
        mListView.setAdapter(adapter);
        mPresenter.getSeries(String.valueOf(page));
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    //------------------------------
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page=1;
        isAdd=false;
        mPresenter.getSeries(String.valueOf(page));
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        isAdd=true;
        mPresenter.getSeries(String.valueOf(++page));
    }


    //--------------------------------------------------------
    @Override
    public void returnSeriesData(SeriesData seriesData) {

        for (int i = 0; i < seriesData.getData().size(); i++) {
            seriesIds.add(seriesData.getData().get(i).getSeriesid());
        }

        if (isAdd){
            if (seriesData.getData().size() == 0) {
                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
            }
            adapter.addRes(seriesData.getData());
        }else {
            adapter.updateRes(seriesData.getData());
        }
        mPullToRefresh.onRefreshComplete();
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        Log.e(TAG, "onError:    "+errorInfo );
    }
}
