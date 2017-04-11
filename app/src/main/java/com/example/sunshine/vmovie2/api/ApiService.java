package com.example.sunshine.vmovie2.api;


import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.bean.MovieListHeadData;
import com.example.sunshine.vmovie2.bean.SeriesData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sunshine on 2017/4/10.
 */

public interface ApiService {
    //系列页面接口
    // http://app.vmoiver.com/apiv3/series/getList?p=1
    @GET("/apiv3/series/getList")
    Observable<SeriesData> getSeriesData(@Query("p") int p);
    //首页最新ViewPager接口
    @GET("/apiv3/index/getBanner")
    Observable<MovieListHeadData> getMovieListViewPager();
    //首页最新页面接口
    @GET("/apiv3/post/getPostByTab")
    Observable<MovieListBean> getMovieList(@Query("p") String pageIndex);
    //首页频道页面接口
    @GET("/apiv3/cate/getList")
    Observable<ChanelListBean> getChanelList();
}
