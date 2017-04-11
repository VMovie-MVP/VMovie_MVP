package com.example.sunshine.vmovie2.ui.home.presenter;

import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;

import rx.Subscriber;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListPresenter extends MovieListContract.MovieListContractPresenter {

    @Override
    public void getMovieList(String pageIndex) {
        mModel.getMovieListDataBean(pageIndex).subscribe(new Subscriber<MovieListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage().toString());
            }

            @Override
            public void onNext(MovieListBean movieListBean) {
                mView.returnMovieListDataBean(movieListBean);
            }
        });
    }
}
