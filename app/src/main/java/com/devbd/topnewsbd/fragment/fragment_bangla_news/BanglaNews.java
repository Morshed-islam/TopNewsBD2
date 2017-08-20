package com.devbd.topnewsbd.fragment.fragment_bangla_news;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.adapter.BanglaNewsCustomFragmentAdapter;
import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanglaNews extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public BanglaNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bangla_news, container, false);

        getActivity().setTitle("বাংলা নিউজ");
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new BanglaNewsCustomFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
