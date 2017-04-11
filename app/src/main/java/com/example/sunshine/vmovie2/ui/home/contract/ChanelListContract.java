package com.example.sunshine.vmovie2.ui.home.contract;

import com.example.mvplibrary.base.BaseModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.base.BaseView;
import com.example.sunshine.vmovie2.bean.ChanelListBean;
import com.example.sunshine.vmovie2.bean.MovieListBean;

import rx.Observable;

/**
 * Created by sunshine on 2017/4/11.
 */

public interface ChanelListContract {
    public abstract class ChanelListContractPresenter extends BasePresenter<ChanelListModelI,ChanelListView> {
        public abstract void getChanelList();

    }

    //定义Model
    interface ChanelListModelI extends BaseModel {
        Observable<ChanelListBean> getChanelListDataBean();
    }

    //定义View
    interface ChanelListView extends BaseView {
        void returnChanelListDataBean(ChanelListBean chanelListBean);

    }
}
