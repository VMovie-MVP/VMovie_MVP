package com.example.sunshine.vmovie2.ui.home.behind.model;

import com.example.mvplibrary.rxevent.AndroidIOToMain;
import com.example.sunshine.vmovie2.api.Api;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindBean;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindContract;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindTitleContract;

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
