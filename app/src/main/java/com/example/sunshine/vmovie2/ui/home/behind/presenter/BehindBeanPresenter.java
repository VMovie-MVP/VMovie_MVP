package com.example.sunshine.vmovie2.ui.home.behind.presenter;

import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindBean;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindContract;
import com.example.sunshine.vmovie2.ui.home.behind.contract.BehindTitleContract;

import rx.Subscriber;

/**
 * Created by MartinYun on 2017/4/11.
 */

public class BehindBeanPresenter extends BehindContract.BehindBeanContractPresenter {


    @Override
    public void getBehindBean(String cateId,int page) {
        mModel.getBehindBean(cateId,page).subscribe(new Subscriber<BehindBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BehindBean behindBean) {
                mView.returnBehindBean(behindBean);
            }
        });
    }
}
