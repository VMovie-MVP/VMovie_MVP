package com.example.sunshine.vmovie2.ui.home.presenter;

import android.util.Log;

import com.example.sunshine.vmovie2.bean.ChanelDetailListBean;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListDetailContract;

import rx.Subscriber;

/**
 * Created by sunshine on 2017/4/11.
 */

public class ChanelListDetailPresenter extends ChanelListDetailContract.ChanelListDetailContractPresenter {

    private static final String TAG = ChanelListDetailPresenter.class.getSimpleName();

    @Override
    public void getChanelListDetail(String cateId, String pageIndex) {
        mModel.getChanelListDetailDataBean(cateId, pageIndex).subscribe(new Subscriber<ChanelDetailListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage().toString());
            }

            @Override
            public void onNext(ChanelDetailListBean chanelDetailListBean) {
                Log.e(TAG, "onNext: " );
                mView.returnChanelListDetailDataBean(chanelDetailListBean);
            }
        });
    }

    @Override
    public void getChanelListDetailTop(String tab) {
        mModel.getChanelListDetailTopData(tab).subscribe(new Subscriber<ChanelDetailListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("errorInfo" + e.getCause() + "\t" + e.getMessage().toString());
            }

            @Override
            public void onNext(ChanelDetailListBean chanelDetailListBean) {
                Log.e(TAG, "onNext: "+chanelDetailListBean );
                mView.returnChanelListDetailDataBean(chanelDetailListBean);
            }
        });
    }
}
