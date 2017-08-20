package com.devbd.topnewsbd.fragment.fragment_jugantor;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.adapter.JuganntorCustomFragmentAdapter;
import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;
import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.LatestNewsBBCBangla;
import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.TopViewNewsBBCBangla;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jugantor extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Jugantor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jugantor, container, false);
        getActivity().setTitle("যুগান্তর");
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new JuganntorCustomFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){

                    case 0:
                        new LatestNewsJugantor();
                        //Toast.makeText(getContext(), "Tab 1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        new TopViewNewsJugantor();
                        //Toast.makeText(getContext(), "Tab 2 selected", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;

    }





}
