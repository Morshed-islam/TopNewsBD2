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
import com.vstechlab.easyfonts.EasyFonts;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TopNewsTopDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvNewsPaperName;
    private TextView tvDetails;
    private ImageView imgUrl;
    private String url;
    private int color = R.color.white;
    private Button mDetails;
    private String title ="টপ নিউজ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news_top_details);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvNewsPaperName = (TextView) findViewById(R.id.tv_NewsPaperName);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        imgUrl = (ImageView) findViewById(R.id.imgView);
        mDetails = (Button) findViewById(R.id.details);


        tvTitle.setTypeface(EasyFonts.robotoBold(this));
        tvDetails.setTypeface(EasyFonts.robotoThin(this));

        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.TOPNEWS_TOP_DETAIL_INFO);

        Log.i("morshed",url);

//        Log.i("morshed",url);
//        System.out.println("the fuck url is here: "+url);

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AllNewsDetailsWebActivity.class);
                intent.putExtra(Constant.ALL_NEWS_DETAIL_INFO, url);
//                intent.putExtra(Constant.BDNEWS_TITLE, title);
                startActivity(intent);

            }
        });

        new TopNewsTopDetailsJsoup().execute();


    }


    private class TopNewsTopDetailsJsoup extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        private String getTitleValue;
        private String getDateValue;
        private String getNewsPaperNAme;
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

                //Simplified
//                Elements simplifiedData = doc.select("dev.w-article");

                //getting news title
                Element mTitle = doc.select("h1.w-article-title").first();
                //getting news date
                Elements mDate = doc.select("div.w-article-meta > div.w-article-time");
                Elements mNewsPaper = doc.select("div.w-article-meta > span");

                Log.i("morshed",mTitle.text());
                Log.i("morshed",mDate.text());
                Log.i("morshed",mNewsPaper.text());

                for(int i=0; i< mDate.size(); i++){
                    getDateValue = mDate.get(i).text();
                    Log.i("morshed"+"date is here",getDateValue);
                }

                //getting news details
//                Elements mDetails = doc.select("div#newsDtl");
                Elements mDetails = doc.select("section.w-article-content > p");

//                Log.i("morshed",mDetails.text());
//                for (int j=0; j<2; j++) {
//                    description[j] = mDetails.get(j).text();
//                    Log.i("morshed"+"Details is here:",description[j]);
//                }


                getTitleValue = mTitle.text();
                getNewsPaperNAme = mNewsPaper.text();
                getDetailsValue = mDetails.text();
//




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
//
//            String finalDes ="";
//            for (int i=0; i<description.length; i++){
//                finalDes = finalDes+description[i]+"\n\n";
//            }


            tvDetails.setText(getDetailsValue);
            tvTitle.setText(getTitleValue);
            tvDate.setText(getDateValue);
            tvNewsPaperName.setText(getNewsPaperNAme);



        }
    }

    private Context getDialogContext(){
        Context context;
        if(getApplicationContext() != null)
            context = TopNewsTopDetailsActivity.this;
        else
            context = TopNewsTopDetailsActivity.this;

        return context;
    }
}
