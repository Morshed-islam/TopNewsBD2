package com.devbd.topnewsbd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.devbd.topnewsbd.adapter.TopNewsCustomFragmentPageAdapter;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.fragment.fragment_bangla_news.BanglaNews;
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
import com.devbd.topnewsbd.theme_manager.SettingsActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0){
            setTheme(Constant.theme);
        }else if (appTheme == 0){
            setTheme(Constant.theme);
        }else{
            setTheme(appTheme);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);


        } else if (id == R.id.kaler_kantho) {
            fragment = new Kalerkantho();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.bdnews) {
            fragment = new BDNews();
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
        else if (id == R.id.bbc_bangla) {
            fragment = new BBCBangla();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.samakal) {
            fragment = new Samakal();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.bangladesh_protidin) {

            fragment = new BangladeshProtidin();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        } else if (id == R.id.ntv_news) {

            fragment = new NtvNews();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
       }
        else if (id == R.id.theme){

            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

           // overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }else if (id == R.id.nav_share){
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container_wrapper, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
