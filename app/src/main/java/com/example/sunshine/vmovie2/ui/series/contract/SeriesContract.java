package com.example.sunshine.vmovie2.ui.series.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.bean.SeriesData;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public interface SeriesContract {

    public abstract class SerciseContractPresenter extends BasePresenter<SeriesModelI,SeriesView>{
        public abstract void getSeries(String pageIndex);
    }
    //定义Model
    interface SeriesModelI extends BaseModel {
        Observable<SeriesData> getSeriesData(String pageIndex);
    }
    //定义View
    interface SeriesView extends BaseView {
        void returnSeriesData(SeriesData seriesData);


    }
}
