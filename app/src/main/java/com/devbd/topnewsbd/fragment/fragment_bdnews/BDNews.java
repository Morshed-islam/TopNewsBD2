package com.devbd.topnewsbd.fragment.fragment_bdnews;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.adapter.BDNewsCustomFragmentAdapter;
import com.devbd.topnewsbd.adapter.TestViewerPagerAdapter;
import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;
import com.devbd.topnewsbd.model.bdnews_model.BdNewsLatestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class BDNews extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mFragments = new Vector<Fragment>();
    private BDNewsCustomFragmentAdapter newsCustomFragmentAdapter;

    public BDNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bdnews, container, false);
        getActivity().setTitle("বিডি নিউজ");

        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
//        mFragments.add(new LatestNewsBDNews());
//        mFragments.add(new TopViewNewsBDNews());

        //TODO new code apply
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        newsCustomFragmentAdapter = new BDNewsCustomFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(newsCustomFragmentAdapter);
//        setupViewPager(viewPager);

        viewPager.setAdapter(new BDNewsCustomFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


//        final TabLayout.Tab tab1 = tabLayout.newTab().setText("Latest");
//        final TabLayout.Tab tab2 = tabLayout.newTab().setText("Top Views");

//        tabLayout.addTab(tab1,0);
//        tabLayout.addTab(tab2,1);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){

                    case 0:
                        new LatestNewsBDNews();
                        //Toast.makeText(getContext(), "Tab 1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        new TopViewNewsBDNews();
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


//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
//        adapter.addFrag(new LatestNewsBDNews(), "Latest News");
//        adapter.addFrag(new TopViewNewsBDNews(), "Top Views");
//        viewPager.setAdapter(adapter);
//    }


//    public class ViewPagerAdapter extends FragmentPagerAdapter{
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFrag(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }

}
