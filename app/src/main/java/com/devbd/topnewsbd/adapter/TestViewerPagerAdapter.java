package com.devbd.topnewsbd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.devbd.topnewsbd.fragment.fragment_bdnews.LatestNewsBDNews;
import com.devbd.topnewsbd.fragment.fragment_bdnews.TopViewNewsBDNews;

/**
 * Created by morshed on 7/25/17.
 */

public class TestViewerPagerAdapter extends FragmentPagerAdapter {
    public TestViewerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

   /* @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LatestNewsBDNews latestNews = new LatestNewsBDNews();
                return latestNews;
            case 1:
                TopViewNewsBDNews topViewNews = new TopViewNewsBDNews();
                return topViewNews;



    }
        return null;
    }*/
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
}
