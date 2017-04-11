package com.example.sunshine.vmovie2.ui.home.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListContract;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelListModel implements ChanelListContract.ChanelListModelI{

    @Override
    public Observable<ChanelListBean> getChanelListDataBean() {
        return Api.getApiService().getChanelList().compose(new AndroidIOToMain.IOToMainTransformer<ChanelListBean>());
    }
}
