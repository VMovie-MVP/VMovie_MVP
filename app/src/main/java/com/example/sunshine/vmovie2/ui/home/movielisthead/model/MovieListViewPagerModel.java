package com.example.sunshine.vmovie2.ui.home.movielisthead.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.MovieListHeadData;
import com.example.sunshine.vmovie2.ui.home.movielisthead.contract.MovieListViewPagerContract;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public class MovieListViewPagerModel implements MovieListViewPagerContract.MovieListViewPagerModelI {


    @Override
    public Observable<MovieListHeadData> getMovieListViewPagerListBean() {
        return Api.getApiService().getMovieListViewPager().compose(new AndroidIOToMain.IOToMainTransformer<MovieListHeadData>());

    }
}
