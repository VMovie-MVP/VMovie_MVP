package com.example.sunshine.vmovie2.ui.series.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.bean.SeriesData;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;
import com.example.sunshine.vmovie2.ui.series.contract.SeriesContract;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public class SeriesModel implements SeriesContract.SeriesModelI{
    @Override
    public Observable<SeriesData> getSeriesData(String pageIndex) {
        return Api.getApiService().getSeriesData(pageIndex).compose(new AndroidIOToMain.IOToMainTransformer<SeriesData>());
    }
}
