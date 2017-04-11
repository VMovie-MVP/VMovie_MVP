package com.example.sunshine.vmovie2.ui.home.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.bean.MovieListBean;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public interface MovieListContract {
    public abstract class  MovieListContractPresenter extends BasePresenter<MovieListModelI,MovieListView> {
        public abstract void getMovieList(String pageIndex);

    }

    //定义Model
    interface MovieListModelI extends BaseModel {
        Observable<MovieListBean> getMovieListDataBean(String pageIndex);
    }

    //定义View
    interface MovieListView extends BaseView {
        void returnMovieListDataBean(MovieListBean movieListBean);

    }
}
