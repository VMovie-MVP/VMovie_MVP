package com.example.sunshine.vmovie2.ui.home.presenter;

import com.example.sunshine.vmovie2.bean.MovieListHeadData;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListViewPagerContract;

import rx.Subscriber;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListViewPagerPresenter extends MovieListViewPagerContract.MovieListViewPagerPresenter {
    @Override
    public void getMovieListViewPager() {
        mModel.getMovieListViewPagerListBean().subscribe(new Subscriber<MovieListHeadData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage());
            }

            @Override
            public void onNext(MovieListHeadData movieListHeadData) {
                mView.returnMovieListPagerViewBean(movieListHeadData);
            }
        });
    }
}
