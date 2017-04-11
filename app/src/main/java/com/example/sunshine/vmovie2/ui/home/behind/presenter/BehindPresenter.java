package com.example.sunshine.vmovie2.ui.home.behind.presenter;

import com.example.mvplibrary.base.BasePresenter;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindTitleContract;

import rx.Subscriber;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindPresenter extends BehindTitleContract.BehindContractPresenter {

    @Override
    public void getTitle() {
        mModel.getBehindTitleBean().subscribe(new Subscriber<BehindTitleBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BehindTitleBean behindTitleBean) {
                mView.returnBehindTitleBean(behindTitleBean);
            }
        });
    }
}
