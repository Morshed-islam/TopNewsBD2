package com.devbd.topnewsbd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_prothom_alo.LatestNewsProthomAlo;
import com.devbd.topnewsbd.fragment.fragment_prothom_alo.TopViewNewsProthomAlo;

/**
 * Created by morshed on 7/18/17.
 */

public class ProthomAloCustomFragmentAdapter extends FragmentPagerAdapter {
    public ProthomAloCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsProthomAlo latestNews = new LatestNewsProthomAlo();
                return latestNews;
            case 1:
                TopViewNewsProthomAlo topViewNews = new TopViewNewsProthomAlo();
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
