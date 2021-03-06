package com.example.sunshine.vmovie2.ui.home.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseFragment;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.HeadViewViewPager;
import com.example.sunshine.vmovie2.ui.home.activity.MovieDetailActivity;
import com.example.sunshine.vmovie2.ui.home.adapter.MovieListAdapter;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;
import com.example.sunshine.vmovie2.ui.home.model.MovieListModel;
import com.example.sunshine.vmovie2.ui.home.presenter.MovieListPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment<MovieListPresenter, MovieListModel> implements PullToRefreshBase.OnRefreshListener2, MovieListContract.MovieListView, AdapterView.OnItemClickListener {
    public static final String TAG = MovieListFragment.class.getName();
    @BindView(R2.id.movie_list_lv)
    PullToRefreshListView mPullToRefresh;
    private ListView mListView;
    private MovieListAdapter adapter;

    private int index=1;
    private boolean isAdd;

    private List<String> postIds = new ArrayList<>();
    private List<String> likeNum = new ArrayList<>();
    private List<String> shareNum = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    public void initView() {
        mPullToRefresh.setOnRefreshListener(this);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mListView = mPullToRefresh.getRefreshableView();

        HeadViewViewPager headViewViewPager = new HeadViewViewPager(getActivity());
        FragmentManager childFragmentManager = getChildFragmentManager();
        headViewViewPager.setFragmentManger(childFragmentManager);
        mListView.addHeaderView(headViewViewPager.getHeadView());
        FragmentActivity activity=getActivity();
        adapter = new MovieListAdapter(null, getContext(), null,activity, R.layout.movie_list_item);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mPresenter.getMovieList(String.valueOf(index));
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void returnMovieListDataBean(MovieListBean movieListBean) {
        if (!isAdd) {
            adapter.updateRes(movieListBean.getData());
        } else {
            if (movieListBean.getData().size() == 0) {
                Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
            }
            adapter.addRes(movieListBean.getData());
        }
        for (int i = 0; i < movieListBean.getData().size(); i++) {
            postIds.add(movieListBean.getData().get(i).getPostid());
            likeNum.add(movieListBean.getData().get(i).getLike_num());
            shareNum.add(movieListBean.getData().get(i).getShare_num());
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("post_id", postIds.get(position - 2));
        intent.putExtra("like_num", likeNum.get(position - 2));
        intent.putExtra("share_num", shareNum.get(position - 2));
        startActivity(intent);

    }
}
