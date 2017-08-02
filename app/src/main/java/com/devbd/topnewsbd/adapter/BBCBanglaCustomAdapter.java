package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin.LatestNewsBangladeshProtidin;
import com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin.TopViewNewsBangladeshProtidin;
import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.LatestNewsBBCBangla;
import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.TopViewNewsBBCBangla;
import com.devbd.topnewsbd.fragment.fragment_bdnews.TopViewNewsBDNews;

/**
 * Created by morshed on 7/18/17.
 */

public class BBCBanglaCustomAdapter extends FragmentPagerAdapter {
    public BBCBanglaCustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LatestNewsBBCBangla();

            default:
                // The other sections of the app are dummy placeholders.
                Fragment fragment = new TopViewNewsBBCBangla();
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
