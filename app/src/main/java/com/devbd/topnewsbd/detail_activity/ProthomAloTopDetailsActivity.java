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

public class ProthomAloTopDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDetails;
    private ImageView imgUrl;
    private String url;
    private int color = R.color.white;
    private Button mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prothom_alo_top_details);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("প্রথম আলো");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        imgUrl = (ImageView) findViewById(R.id.imgView);
        mDetails = (Button) findViewById(R.id.details);

        tvTitle.setTypeface(EasyFonts.robotoBold(this));
        tvDetails.setTypeface(EasyFonts.robotoThin(this));

        //get the url from the //TODO PROTHOM_ALO_TOP_DETAIL_INFO Constant
        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.PROTHOM_ALO_TOP_DETAIL_INFO);




        Log.i("morshed",url);

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //this is a critical data pair value
                // After getting the url value using //TODO PROTHOM_ALO_TOP_DETAIL_INFO
                // just same value pass through //TODO PROTHOM_ALO_LATEST_DETAIL_INFO
                // After  using this logic u don't have to create a another activity to-
                //show the webView data just using the only one WebView Activity got all data
                //that's amazing
                Intent intent = new Intent(getApplicationContext(), AllNewsDetailsWebActivity.class);
                intent.putExtra(Constant.ALL_NEWS_DETAIL_INFO, url);
                startActivity(intent);

            }
        });

        new ProthomALoTopDetailsJsoup().execute();



    }


    private class ProthomALoTopDetailsJsoup extends AsyncTask<Void,Void,Boolean>{

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

                //getting news title
                Element mTitle = doc.select("div.right_title > h1").first();
                //getting news date
                final Element mDate = doc.select("div.time > span").first();
                //getting news details
//                Element mDetails = doc.select("p").first();
              Elements mDetails = doc.select("p");

                for (int i =0; i<2; i++){
                    description[i] = mDetails.get(i).text();

                }



                try {
                    getTitleValue = mTitle.text();
                    getDateValue = mDate.text();
                    getDetailsValue = mDetails.text();


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


            String finalDes ="";
            for (int i=0; i<description.length; i++){
                finalDes = finalDes+description[i]+"\n\n";
            }


            tvTitle.setText(getTitleValue);
            tvDate.setText(getDateValue);
            tvDetails.setText(finalDes);

//            for (int i=1; i<=description.length; i++){
//                tvDetails.setText(description[i]);
//            }

          //  Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);


        }
    }

    private Context getDialogContext(){
        Context context;
        if(getApplicationContext() != null)
            context = ProthomAloTopDetailsActivity.this;
        else
            context = ProthomAloTopDetailsActivity.this;

        return context;
    }
}
