package com.example.sunshine.vmovie2.ui.behind.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.ui.behind.bean.BehindBean;
import com.example.sunshine.vmovie2.ui.behind.contract.BehindContract;

import rx.Observable;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindBeanModel implements BehindContract.BehindBeanContractModel{

    @Override
    public Observable<BehindBean> getBehindBean(String cateId, int page) {
       return Api.getApiService().getBehindBean(cateId,page).compose(new AndroidIOToMain.IOToMainTransformer<BehindBean>());
    }
}
