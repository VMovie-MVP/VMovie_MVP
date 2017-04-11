package com.example.sunshine.vmovie2.ui.home.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvplibrary.base.BaseActivity;
import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.R2;
import com.example.sunshine.vmovie2.bean.ChanelDetailListBean;
import com.example.sunshine.vmovie2.ui.behind.BehindDetailActivity;
import com.example.sunshine.vmovie2.ui.home.adapter.AlbumAdapter;
import com.example.sunshine.vmovie2.ui.home.adapter.ChanelDetailAdapter;
import com.example.sunshine.vmovie2.ui.home.adapter.HotAdapter;
import com.example.sunshine.vmovie2.ui.home.contract.ChanelListDetailContract;
import com.example.sunshine.vmovie2.ui.home.model.ChanelListDetailModel;
import com.example.sunshine.vmovie2.ui.home.presenter.ChanelListDetailPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sunshine on 2017/4/11.
 */
public class ChanelDetailActivity extends BaseActivity<ChanelListDetailPresenter, ChanelListDetailModel> implements PullToRefreshBase.OnRefreshListener2, View.OnClickListener, AdapterView.OnItemClickListener, ChanelListDetailContract.ChanelListDetailView {

    private static final String TAG = ChanelDetailActivity.class.getSimpleName();
    @BindView(R2.id.chanel_detail_list_lv)
    PullToRefreshListView pullToRefresh;
    @BindView(R2.id.chanel_detail_top_title)
    TextView titleView;

    private ListView mListView;
    private Intent intent;
    private int pos;
    private ChanelDetailAdapter adapter;
    private HotAdapter hotAdapter;
    private AlbumAdapter albumAdapter;

    private List<String> likeNum = new ArrayList<>();
    private List<String> shareNum = new ArrayList<>();
    private List<String> requestUrl = new ArrayList<>();
    private String cateId;
    private int page = 1;

    private boolean isAdd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chanel_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        pullToRefresh.setOnRefreshListener(this);
        pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);

        mListView = pullToRefresh.getRefreshableView();
        intent = getIntent();
        pos = intent.getIntExtra("position", 0);
        adapter = new ChanelDetailAdapter(null, this, null, R.layout.chanel_detail_lv_item1);
        hotAdapter = new HotAdapter(null, this, null, R.layout.chanel_detail_lv_item1);
        albumAdapter = new AlbumAdapter(null, this, null, R.layout.chanel_detail_lv_item2);

        if (pos == 0) {
            final String hot=intent.getStringExtra("hot");
            Log.e(TAG, "initView: 获得的hot为---->"+hot );
            String hotName=intent.getStringExtra("hotName");
            titleView.setText(hotName);
            mPresenter.getChanelListDetailTop(hot);
            mListView.setAdapter(hotAdapter);
        } else if (pos == 1) {
            final String album=intent.getStringExtra("album");
            String albumName=intent.getStringExtra("albumName");
            titleView.setText(albumName);
            mPresenter.getChanelListDetailTop(album);
            mListView.setAdapter(albumAdapter);
        } else {
            cateId = intent.getStringExtra("cateId");
            String cateName = intent.getStringExtra("cateName");
            titleView.setText(cateName);
            mPresenter.getChanelListDetail(cateId, String.valueOf(page));
            mListView.setAdapter(adapter);
        }

        findViewById(R.id.chanel_detail_top_back).setOnClickListener(this);
        mListView.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chanel_detail_top_back:
                finish();
                break;
        }
    }

    //=============ListView 单条点击事件监听=========================
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (pos == 1) {
            Intent intent = new Intent(this, BehindDetailActivity.class);
            intent.putExtra("like_num", likeNum.get(position - 1));
            intent.putExtra("share_num", shareNum.get(position - 1));
            intent.putExtra("request_url", requestUrl.get(position - 1));
//            intent.putExtra("comment_num",)
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ChanelFinalDetailActivity.class);
            intent.putExtra("like_num", likeNum.get(position - 1));
            intent.putExtra("share_num", shareNum.get(position - 1));
            intent.putExtra("request_url", requestUrl.get(position - 1));
            startActivity(intent);
        }

    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        isAdd = false;
        page = 1;
        mPresenter.getChanelListDetail(cateId, String.valueOf(page));
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        isAdd = false;
        mPresenter.getChanelListDetail(cateId, String.valueOf(++page));
    }

    @Override
    public void returnChanelListDetailDataBean(ChanelDetailListBean chanelDetailListBean) {
        Log.e(TAG, "returnChanelListDetailDataBean: "+chanelDetailListBean );
        for (int i = 0; i < chanelDetailListBean.getData().size(); i++) {
            likeNum.add(chanelDetailListBean.getData().get(i).getLike_num());
            shareNum.add(chanelDetailListBean.getData().get(i).getShare_num());
            requestUrl.add(chanelDetailListBean.getData().get(i).getRequest_url());

        }
        if (!isAdd) {
            hotAdapter.updateRes(chanelDetailListBean.getData());
            albumAdapter.updateRes(chanelDetailListBean.getData());
            adapter.updateRes(chanelDetailListBean.getData());
        } else {
            if (chanelDetailListBean.getData().size() == 0) {
                Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
            }
            hotAdapter.addRes(chanelDetailListBean.getData());
            albumAdapter.addRes(chanelDetailListBean.getData());
            adapter.addRes(chanelDetailListBean.getData());
        }

        pullToRefresh.onRefreshComplete();

    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        Log.e(TAG, "onError: "+errorInfo );
    }
}
