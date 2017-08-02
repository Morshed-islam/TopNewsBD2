package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_jugantor.LatestNewsJugantor;
import com.devbd.topnewsbd.fragment.fragment_jugantor.TopViewNewsJugantor;
import com.devbd.topnewsbd.fragment.fragment_kalerkantho.TopViewNewsKalerkantho;

/**
 * Created by morshed on 7/18/17.
 */

public class JuganntorCustomFragmentAdapter extends FragmentPagerAdapter {
    public JuganntorCustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsJugantor latestNews = new LatestNewsJugantor();
                return latestNews;
            default:
                Fragment fragment = new TopViewNewsJugantor();
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
