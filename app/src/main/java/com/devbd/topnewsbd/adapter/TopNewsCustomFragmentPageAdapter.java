package com.devbd.topnewsbd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_top_news.LatestNewsTop;
import com.devbd.topnewsbd.fragment.fragment_top_news.TopViewNewsTop;

/**
 * Created by morshed on 7/14/17.
 */

public class TopNewsCustomFragmentPageAdapter extends FragmentPagerAdapter {
    public TopNewsCustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsTop latestNews = new LatestNewsTop();
                return latestNews;
            case 1:
                TopViewNewsTop topViewNews = new TopViewNewsTop();
                return topViewNews;
        }

        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "সর্বশেষ";
            case 1:
                return "সর্বাধিক পঠিত";
        }
        return null;
    }
}


