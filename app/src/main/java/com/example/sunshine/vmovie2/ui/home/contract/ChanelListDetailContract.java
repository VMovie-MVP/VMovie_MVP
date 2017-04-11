package com.example.sunshine.vmovie2.ui.home.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.bean.ChanelDetailListBean;
import com.example.sunshine.vmovie2.bean.ChanelListBean;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public interface ChanelListDetailContract {
    public abstract class ChanelListDetailContractPresenter extends BasePresenter<ChanelListDetailModelI,ChanelListDetailView> {
        public abstract void getChanelListDetail(String cateId,String pageIndex);
        public abstract void getChanelListDetailTop(String tab);
    }

    //定义Model
    interface ChanelListDetailModelI extends BaseModel {
        Observable<ChanelDetailListBean> getChanelListDetailDataBean(String cateId,String pageIndex);
        Observable<ChanelDetailListBean> getChanelListDetailTopData(String tab);
    }


    //定义View
    interface ChanelListDetailView extends BaseView {
        void returnChanelListDetailDataBean(ChanelDetailListBean chanelDetailListBean);

    }
}
