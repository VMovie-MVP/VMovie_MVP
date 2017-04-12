package com.example.sunshine.vmovie2.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sunshine.vmovie2.R;
import com.example.sunshine.vmovie2.ui.behind.BehindDetailActivity;
import com.example.sunshine.vmovie2.ui.home.activity.MovieDetailActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

/**
 * Created by pc on 2017/3/30.
 */

public class MovieListHeadViewFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = MovieListHeadViewFragment.class.getName();
    private String Url;
    private String type;
    private String param;

    public void setType(String type) {
        this.type = type;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getType() {
        return type;
    }

    public String getParam() {
        return param;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_viewpager_fragment, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.movie_list_viewpager_image);

        imageView.setOnClickListener(this);

        Picasso.with(getContext())

                .load(getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        //  Log.e("TAG", "onCreateView: +++++++++++++" +getUrl());
        //  container.addView(view);
        return view;

    }

    @Override
    public void onClick(View v) {
        if (getType().equals("1")) {
            //跳转webView
            Intent intent = new Intent(getActivity(), BehindDetailActivity.class);
            intent.putExtra("request_url", getParam());
            intent.putExtra("header", "header");
            intent.putExtra("like_num", "like_num");
            intent.putExtra("share_num", "share_num");
            getActivity().startActivity(intent);

        } else if (getType().equals("2")) {
            //跳转视频和WebView
          //  String request_url = new String("http://app.vmoiver.com/" + getParam() + "?qingapp=app_new");
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("post_id", getParam());
            intent.putExtra("like_num", "985");
            intent.putExtra("share_num", "211");

            getActivity().startActivity(intent);


        }

    }
}
