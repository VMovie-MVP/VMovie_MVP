package com.example.sunshine.vmovie2.ui.home.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.ChanelDetailListBean;
import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListContract;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListDetailContract;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelListDetailModel implements ChanelListDetailContract.ChanelListDetailModelI{


    @Override
    public Observable<ChanelDetailListBean> getChanelListDetailDataBean(String cateId, String pageIndex) {
        return Api.getApiService().getChanelListDetail(cateId,pageIndex).compose(new AndroidIOToMain.IOToMainTransformer<ChanelDetailListBean>());
    }

    @Override
    public Observable<ChanelDetailListBean> getChanelListDetailTopData(String tab) {
        return Api.getApiService().getChanelListDetailTop(tab).compose(new AndroidIOToMain.IOToMainTransformer<ChanelDetailListBean>());
    }
}
