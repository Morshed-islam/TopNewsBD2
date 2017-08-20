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

public class JugantorLatestDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDetails;
    private ImageView imgUrl;
    private String url;
    private int color = R.color.white;
    private Button mDetails;
    private String title ="যুগান্তর";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juganton_latest_details);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        imgUrl = (ImageView) findViewById(R.id.imgView);
        mDetails = (Button) findViewById(R.id.details);


        tvTitle.setTypeface(EasyFonts.robotoBold(this));
        tvDetails.setTypeface(EasyFonts.robotoThin(this));

        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.JUGANTOR_LATEST_DETAIL_INFO);


//        Log.i("morshed",url);
        System.out.println("the fuck url is here: "+url);

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AllNewsDetailsWebActivity.class);
                intent.putExtra(Constant.ALL_NEWS_DETAIL_INFO, url);
//                intent.putExtra(Constant.BDNEWS_TITLE, title);
                startActivity(intent);

            }
        });

        new JugantorLatestDetailsJsoup().execute();


    }


    private class JugantorLatestDetailsJsoup extends AsyncTask<Void,Void,Boolean>{


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

                //http://bangla.bdnews24.com
                //getting news title
                Element mTitle = doc.select("div.dtl_pg_row > div#hl2").first();
                //getting news date
                Elements mDate = doc.select("div.dtltop_left > span");
                for(int i=0; i< mDate.size(); i++){
                    getDateValue = mDate.get(i).text();
                    Log.i("morshed"+"date is here",getDateValue);
                }


                //getting news details
//                Element mDetails = doc.select("div.custombody > p").first();
                Element mDetails = doc.select("div#myText").first();



//                for (int j=0; j<2; j++) {
//                    description[j] = mDetails.get(j).text();
//                    Log.i("morshed"+"Data is here:",description[j]);
//                }


                try {
                    getTitleValue = mTitle.text();
//                    getDateValue = mDate.text();
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

//            String finalDes ="";
//            for (int i=0; i<description.length; i++){
//                finalDes = finalDes+description[i]+"\n\n";
//            }


            tvDetails.setText(getDetailsValue);
            tvTitle.setText(getTitleValue);
            tvDate.setText(getDateValue);



        }
    }

    private Context getDialogContext(){
        Context context;
        if(getApplicationContext() != null)
            context = JugantorLatestDetailsActivity.this;
        else
            context = JugantorLatestDetailsActivity.this;

        return context;
    }
}
