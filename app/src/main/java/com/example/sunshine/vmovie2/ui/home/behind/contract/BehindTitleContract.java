package com.example.sunshine.vmovie2.ui.home.behind.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.ui.home.behind.bean.BehindTitleBean;
import com.example.sunshine.vmovie2.ui.home.behind.presenter.BehindPresenter;

import rx.Observable;

/**
 * Created by MartinYun on 2017/4/11.
 */

public interface BehindTitleContract {

    public abstract class BehindContractPresenter extends BasePresenter<BehinContractdModel, BehindTitleView> {
        public abstract void getTitle();
    }


    interface BehinContractdModel extends BaseModel {
        Observable<BehindTitleBean> getBehindTitleBean();
    }

    interface BehindTitleView extends BaseView {
        void returnBehindTitleBean(BehindTitleBean behindTitleBean);
    }


}
