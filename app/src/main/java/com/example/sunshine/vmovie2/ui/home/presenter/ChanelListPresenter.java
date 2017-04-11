package com.example.sunshine.vmovie2.ui.home.presenter;

import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.bean.MovieListBean;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListContract;
import com.example.sunshine.vmovie2.ui.home.contract.MovieListContract;

import rx.Subscriber;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelListPresenter extends ChanelListContract.ChanelListContractPresenter {


    @Override
    public void getChanelList() {
        mModel.getChanelListDataBean().subscribe(new Subscriber<ChanelListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage().toString());
            }

            @Override
            public void onNext(ChanelListBean chanelListBean) {
                mView.returnChanelListDataBean(chanelListBean);
            }
        });
    }
}
