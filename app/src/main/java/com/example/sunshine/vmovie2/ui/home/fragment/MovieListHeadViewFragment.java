package com.example.sunshine.vmovie2.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sunshine.vmovie2.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

/**
 * Created by pc on 2017/3/30.
 */

public class MovieListHeadViewFragment extends Fragment {
    public static final String TAG =MovieListHeadViewFragment.class.getName() ;
    private String Url;

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
        Picasso.with(getContext())

                .load(getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
      //  Log.e("TAG", "onCreateView: +++++++++++++" +getUrl());
      //  container.addView(view);
        return view;

    }
}
