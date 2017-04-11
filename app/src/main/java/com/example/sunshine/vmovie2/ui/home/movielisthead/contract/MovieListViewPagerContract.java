package com.example.sunshine.vmovie2.ui.home.movielisthead.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.bean.MovieListHeadData;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public interface MovieListViewPagerContract {

    public abstract class MovieListViewPagerPresenter extends BasePresenter<MovieListViewPagerModelI,MovieListViewPagerView>{
        public abstract void getMovieListViewPager();
    }
    //定义Model
    interface MovieListViewPagerModelI extends BaseModel {
        Observable<MovieListHeadData> getMovieListViewPagerListBean();
    }
    //定义View
    interface MovieListViewPagerView extends BaseView {
        void returnMovieListPagerViewBean(MovieListHeadData movieListHeadData);


    }
}
