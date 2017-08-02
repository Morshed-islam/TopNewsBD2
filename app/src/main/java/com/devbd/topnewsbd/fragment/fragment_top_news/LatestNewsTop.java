package com.devbd.topnewsbd.fragment.fragment_top_news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbd.topnewsbd.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsTop extends Fragment {


    public LatestNewsTop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_latest_news, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("morshed","Latest start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("morshed","Latest resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("morshed","Latest pause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("morshed","Latest attach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("morshed","Latest detach");
    }


}
