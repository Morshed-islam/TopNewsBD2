package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bdnews.LatestNewsBDNews;
import com.devbd.topnewsbd.fragment.fragment_bdnews.TopViewNewsBDNews;

import java.util.List;
import java.util.Vector;

/**
 * Created by morshed on 7/18/17.
 */

public class BDNewsCustomFragmentAdapter extends FragmentPagerAdapter {


    public BDNewsCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // The first section of the app is the most interesting -- it offers
                // a launchpad into the other demonstrations in this example application.
                return new LatestNewsBDNews();

            default:
                // The other sections of the app are dummy placeholders.
                Fragment fragment = new TopViewNewsBDNews();
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
