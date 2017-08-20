package com.devbd.topnewsbd.detail_activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.webView_activity.AllNewsDetailsWebActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.vstechlab.easyfonts.EasyFonts;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NtvNewsLatestDetailsActivity extends AppCompatActivity {


    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDetails;
    private ImageView imgUrl;
    private String url;
    private int color = R.color.white;
    private Button mDetails;

    //title
    private String title ="এনটিভি নিউজ";
    InterstitialAd interstitialAd;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ntv_news_latest_details);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mAdView = (AdView) findViewById(R.id.adView);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        imgUrl = (ImageView) findViewById(R.id.imgView);
        mDetails = (Button) findViewById(R.id.details);


        tvTitle.setTypeface(EasyFonts.robotoBold(this));
        tvDetails.setTypeface(EasyFonts.robotoThin(this));

        admob_integrate();

        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.NTVNEWS_LATEST_DETAIL_INFO);


        Log.i("morshed",url);



        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AllNewsDetailsWebActivity.class);
                intent.putExtra(Constant.ALL_NEWS_DETAIL_INFO, url);
//                intent.putExtra(Constant.KALER_KANTHO_TITLE, title);
                startActivity(intent);

            }
        });

        new NtvNewsLatestDetailsJsoup().execute();

    }


    private class NtvNewsLatestDetailsJsoup extends AsyncTask<Void,Void,Boolean>{


        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        private String getTitleValue;
        private String getDateValue;
        private String getDetailsValue;
        private String finalGetImgLink;
        String [] description = new String[2];

        @Override
        protected Boolean doInBackground(Void... params) {

            Document doc;
            try {

                doc = Jsoup.connect(url)
                        .timeout(10*1000)
                        .get();


                Elements simpilfiedData = doc.select("div.left_content");

                //getting news title
                Element mTitle = simpilfiedData.select("div.headline_section > h1").first();

                //getting news date
                Element mDate = simpilfiedData.select("div.news_date_time > p").first();
//                for(int i=0; i< mDate.size(); i++){
//                    getDateValue = mDate.get(i).text();
//                    Log.i("morshed"+"date is here",getDateValue);
//                }


                //getting news details
//                Element mDetails = doc.select("div.some-class-name2 > p").first();


//              Elements mDetails = doc.select("p");
                //getting news images

                Elements mFinalDetails = doc.select("div.dtl_section > p");

                for (int i =0; i<2; i++){
                    description[i] = mFinalDetails.get(i).text();

                }



                try {
                    getTitleValue = mTitle.text();
                    getDateValue = mDate.text();
//                    getDetailsValue = mDetails.text();


                } catch (Exception e) {

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HelperMethod.startProgressBar(progressDialog,"Loading..");
            mDetails.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            HelperMethod.stopProgressBar(progressDialog);
            mDetails.setVisibility(View.VISIBLE);

            tvTitle.setText(getTitleValue);
            tvDate.setText(getDateValue);


            String finalDes ="";
            for (int i=0; i<description.length; i++){
                finalDes = finalDes+description[i]+"\n\n";
            }
            tvDetails.setText(finalDes);

            //  Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);


        }
    }

    private Context getDialogContext(){
        Context context;
        if(getApplicationContext() != null)
            context = NtvNewsLatestDetailsActivity.this;
        else
            context = NtvNewsLatestDetailsActivity.this;

        return context;
    }




    //  Insterstitial mAdView
    public void displayInterstitialAd(){
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    //for admob integrated
    public void admob_integrate(){
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
