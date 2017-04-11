package com.example.sunshine.vmovie2.ui.series.presenter;

import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.bean.SeriesData;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;
import com.example.sunshine.vmovie2.ui.series.contract.SeriesContract;

import rx.Subscriber;

/**
 * Created by sunshine on 2017/4/11.
 */

public class SeriesPresenter extends SeriesContract.SerciseContractPresenter {

    @Override
    public void getSeries(String pageIndex) {
        mModel.getSeriesData(pageIndex).subscribe(new Subscriber<SeriesData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage().toString());
            }

            @Override
            public void onNext(SeriesData seriesData) {
                mView.returnSeriesData(seriesData);
            }
        });
    }
}
