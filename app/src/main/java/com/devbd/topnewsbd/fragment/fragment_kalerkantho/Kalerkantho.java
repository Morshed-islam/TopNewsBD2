package com.devbd.topnewsbd.fragment.fragment_kalerkantho;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.adapter.KalerKanthoCustomFragmentAdapter;
import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;
import com.devbd.topnewsbd.fragment.fragment_bdnews.LatestNewsBDNews;
import com.devbd.topnewsbd.fragment.fragment_bdnews.TopViewNewsBDNews;

/**
 * A simple {@link Fragment} subclass.
 */
public class Kalerkantho extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private KalerKanthoCustomFragmentAdapter customFragmentAdapter;


    public Kalerkantho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_kalerkantho, container, false);
        getActivity().setTitle("কালের কন্ঠ");

        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);

        customFragmentAdapter = new KalerKanthoCustomFragmentAdapter(getChildFragmentManager());

        viewPager.setAdapter(customFragmentAdapter);
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
                        new LatestNewsKalerkantho();
                        //Toast.makeText(getContext(), "Tab 1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        new TopViewNewsKalerkantho();
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
