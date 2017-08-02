package com.devbd.topnewsbd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bangla_news.LatestNewsBanglaNews;
import com.devbd.topnewsbd.fragment.fragment_bangla_news.TopViewNewsBanglaNews;
import com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin.LatestNewsBangladeshProtidin;
import com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin.TopViewNewsBangladeshProtidin;

/**
 * Created by morshed on 7/18/17.
 */

public class BanglaNewsCustomFragmentAdapter extends FragmentPagerAdapter {
    public BanglaNewsCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsBanglaNews latestNews = new LatestNewsBanglaNews();
                return latestNews;
            case 1:
                TopViewNewsBanglaNews topViewNews = new TopViewNewsBanglaNews();
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
