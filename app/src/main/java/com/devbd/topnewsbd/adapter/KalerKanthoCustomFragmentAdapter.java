package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bdnews.TopViewNewsBDNews;
import com.devbd.topnewsbd.fragment.fragment_kalerkantho.LatestNewsKalerkantho;
import com.devbd.topnewsbd.fragment.fragment_kalerkantho.TopViewNewsKalerkantho;

/**
 * Created by morshed on 7/18/17.
 */

public class KalerKanthoCustomFragmentAdapter extends FragmentPagerAdapter {
    public KalerKanthoCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               return new LatestNewsKalerkantho();

            default:
                // The other sections of the app are dummy placeholders.
                Fragment fragment = new TopViewNewsKalerkantho();
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
