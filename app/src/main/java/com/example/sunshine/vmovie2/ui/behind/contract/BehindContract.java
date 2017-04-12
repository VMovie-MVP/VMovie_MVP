package com.example.sunshine.vmovie2.ui.behind.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.ui.behind.bean.BehindBean;

import rx.Observable;

/**
 * Created by MartinYun on 2017/4/11.
 */

public interface BehindContract {

    public abstract class BehindBeanContractPresenter extends BasePresenter<BehindBeanContractModel, BehindBeanView> {
        public abstract void getBehindBean(String cateId,int page);
    }


    interface BehindBeanContractModel extends BaseModel {
        Observable<BehindBean> getBehindBean(String cateId,int page);
    }

    interface BehindBeanView extends BaseView {
        void returnBehindBean(BehindBean behindBean);
    }


}
