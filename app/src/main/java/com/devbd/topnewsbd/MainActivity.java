package com.devbd.topnewsbd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.devbd.topnewsbd.about.AboutUS;
import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin.BangladeshProtidin;
import com.devbd.topnewsbd.fragment.fragment_bbc_bangla.BBCBangla;
import com.devbd.topnewsbd.fragment.fragment_bdnews.BDNews;
import com.devbd.topnewsbd.fragment.fragment_ntv_news.NtvNews;
import com.devbd.topnewsbd.fragment.fragment_bd24live.Bd24LiveNews;
import com.devbd.topnewsbd.fragment.fragment_jugantor.Jugantor;
import com.devbd.topnewsbd.fragment.fragment_kalerkantho.Kalerkantho;
import com.devbd.topnewsbd.fragment.fragment_prothom_alo.ProthomAloFragment;
import com.devbd.topnewsbd.fragment.fragment_samakal.Samakal;
import com.devbd.topnewsbd.fragment.fragment_top_news.TopNews;
import com.devbd.topnewsbd.UI.SettingsActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TopNewsCustomFragmentPageAdapter customFragmentPageAdapter;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    Constant constant;
    SharedPreferences.Editor editor;
    SharedPreferences app_preferences;
    int appTheme;
    int themeColor;
    int appColor;

    InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Top News BD");
        setSupportActionBar(toolbar);

        admob_integrate();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        customFragmentPageAdapter = new TopNewsCustomFragmentPageAdapter(getSupportFragmentManager());
   /*     mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(customFragmentPageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


//        fragmentManager = getSupportFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragment = new TopNews();
//        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
//        fragmentTransaction.commit();


        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = TopNews.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container_wrapper, fragment).commit();
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),AboutUS.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.top_news) {
            // Handle the camera action
            fragment = new TopNews();

            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.prothom_alo) {
            fragment = new ProthomAloFragment();
            Intent intent = new Intent();
            intent.putExtra(Constant.PROTHOM_ALO_TITLE,"0");

            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);


        }else if (id == R.id.ntv_news) {
            fragment = new NtvNews();
            Intent intent = new Intent();
            intent.putExtra(Constant.NTV_NEWS_TITILE,"1");


            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.kaler_kantho) {
            fragment = new Kalerkantho();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }  else if (id == R.id.bbc_bangla) {
            fragment = new BBCBangla();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.juganntor) {
            fragment = new Jugantor();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.bd24live) {
            fragment = new Bd24LiveNews();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }
        /*else if (id == R.id.bangla_news) {
            fragment = new BanglaNews();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }*/

        else if (id == R.id.bdnews) {
            fragment = new BDNews();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.samakal) {
            fragment = new Samakal();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.bangladesh_protidin) {

            fragment = new BangladeshProtidin();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }  else if (id == R.id.theme) {

            //startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

            // overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.java_programming) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.eu_devbd.javaprogramming")));

        } else if (id == R.id.cgpa) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.devbd.eu.sgpacalculator&hl=en")));

        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/developer?id=Eu%20Dev&hl=en");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container_wrapper, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    //Admod Integrated here

    //  Insterstitial mAdView
    public void displayInterstitialAd(){
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    //for admob integrated
    public void admob_integrate(){
        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //prepare interstitial ad

        interstitialAd = new InterstitialAd(this);
        //insert ad unit
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayInterstitialAd();
            }
        });
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}
