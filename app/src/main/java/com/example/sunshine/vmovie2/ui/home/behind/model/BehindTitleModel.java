package com.example.sunshine.vmovie2.ui.home.behind.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindTitleContract;

import rx.Observable;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindTitleModel implements BehindTitleContract.BehinContractdModel {
    @Override
    public Observable<BehindTitleBean> getBehindTitleBean() {
        return Api.getApiService().getBehindTitleBean().compose(new AndroidIOToMain.IOToMainTransformer<BehindTitleBean>());
    }
}
