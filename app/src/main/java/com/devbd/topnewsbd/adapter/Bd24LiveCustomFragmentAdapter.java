package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.TopViewNewsBBCBangla;
import com.devbd.topnewsbd.fragment.fragment_bd24live.LatestNewsBd24live;
import com.devbd.topnewsbd.fragment.fragment_bd24live.TopViewNewsBd24Live;

/**
 * Created by morshed on 7/18/17.
 */

public class Bd24LiveCustomFragmentAdapter extends FragmentPagerAdapter {
    public Bd24LiveCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsBd24live latestNews = new LatestNewsBd24live();
                return latestNews;
            default:
                // The other sections of the app are dummy placeholders.
                Fragment fragment = new TopViewNewsBd24Live();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;
        }


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
