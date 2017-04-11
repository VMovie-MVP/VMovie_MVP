package com.example.sunshine.vmovie2.ui.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.adapter.MovieListAdapter;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;
import com.example.sunshine.vmovie2.ui.home.model.MovieListModel;
import com.example.sunshine.vmovie2.ui.home.movielisthead.HeadViewViewPager;
import com.example.sunshine.vmovie2.ui.home.presenter.MovieListPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment<MovieListPresenter, MovieListModel> implements PullToRefreshBase.OnRefreshListener2, MovieListContract.MovieListView {
    public static final String TAG = MovieListFragment.class.getName();
    @BindView(R2.id.movie_list_lv)
    PullToRefreshListView mPullToRefresh;
    private ListView mListView;
    private MovieListAdapter adapter;

    private int index=1;
    private boolean isAdd;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public void initView() {
        mPullToRefresh.setOnRefreshListener(this);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefresh.getRefreshableView();

//        HeadViewViewPager headViewViewPager = new HeadViewViewPager(getActivity());
//        FragmentManager childFragmentManager = getChildFragmentManager();
//        headViewViewPager.setFragmentManger(childFragmentManager);
//        mListView.addHeaderView(headViewViewPager.getHeadView());

        adapter = new MovieListAdapter(null, getContext(), null, R.layout.movie_list_item);
        mListView.setAdapter(adapter);
        mPresenter.getMovieList(String.valueOf(index));
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void returnMovieListDataBean(MovieListBean movieListBean) {
        Toast.makeText(getContext(), "movieListBean"+movieListBean.getMsg(), Toast.LENGTH_SHORT).show();
        if (!isAdd) {
            adapter.updateRes(movieListBean.getData());
        } else {
            if (movieListBean.getData().size() == 0) {
                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
            }
            adapter.addRes(movieListBean.getData());
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
//        Log.e(TAG, "onError:    "+errorInfo );

        Toast.makeText(getActivity(), "onError--" + errorInfo, Toast.LENGTH_SHORT).show();
    }

    //------------------ pullToRefresh 上拉下拉刷新 监听 -------
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        isAdd = false;
        index = 1;
        mPresenter.getMovieList(String.valueOf(index));
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        isAdd = true;
        index++;
        mPresenter.getMovieList(String.valueOf(index));
    }

}
