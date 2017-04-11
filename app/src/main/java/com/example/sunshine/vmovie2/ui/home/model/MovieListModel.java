package com.example.sunshine.vmovie2.ui.home.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListModel implements MovieListContract.MovieListModelI{
    @Override
    public Observable<MovieListBean> getMovieListDataBean(String pageIndex) {
        return Api.getApiService().getMovieList(pageIndex).compose(new AndroidIOToMain.IOToMainTransformer<MovieListBean>());
    }
}
