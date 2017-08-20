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
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ProthomAloLatestDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvDetails;
    private ImageView imgUrl;
    private String url;
    private int color = R.color.white;
    private Button mDetails;
    private String title = "প্রথম আলো";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prothom_alo_details);


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
        url = intent.getStringExtra(Constant.PROTHOM_ALO_LATEST_DETAIL_INFO);




        Log.i("morshed",url);

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AllNewsDetailsWebActivity.class);
                intent.putExtra(Constant.ALL_NEWS_DETAIL_INFO, url);
                intent.putExtra(Constant.PROTHOM_ALO_TITLE, title);
                startActivity(intent);

            }
        });

        new ProthomALoLatestDetailsJsoup().execute();



    }


    private class ProthomALoLatestDetailsJsoup extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        private String getTitleValue;
        private String getDateValue;
        private String getDetailsValue;
        private String finalGetImgLink;

        private String [] description = new String[2];

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

                for (int i=0; i<2; i++) {
                    description[i] = mDetails.get(i).text();
                }


                //getting news images
                Element mImgLink = null;
                try{
                    mImgLink = doc.select("a.jw_media_holder media_image > p > img").first();
                    System.out.println(mImgLink);

                }catch (Exception e){
                    Log.i("morshed"+"This is in Catch Exception", String.valueOf(mImgLink));
                }







                    try {
                        getTitleValue = mTitle.text();
                        getDateValue = mDate.text();
                        getDetailsValue = mDetails.text();
                        String getImgLink = null;

                        try {
                            getImgLink = mImgLink.absUrl("src");
                        }catch (Exception e){
                            Log.i("morshed"+"This is in 2nd Catch Exection", getImgLink);

                        }


//                    if (getImgLink == null || getImgLink.isEmpty()) {
//
//                        Log.i("morshed"+"If Null -Within Condition",getImgLink);
//
//                    } else {
//                        Picasso.with(getApplicationContext()).load(getImgLink).into(imgUrl);
//                        Log.i("morshed"+"Else  -Within Condition",getImgLink);
//
//                    }


                        finalGetImgLink = getImgLink;

        /*                runOnUiThread(new Runnable() {
                            public void run() {

                                tvTitle.setText(getTitleValue);
                                tvDate.setText(getDateValue);
                                tvDetails.setText(getDetailsValue);

                                try{
                                    Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);
                                }catch (Exception e){

                                }

                                Log.i("morshed", getTitleValue);
                                Log.i("morshed", getDateValue);
                                Log.i("morshed",getDetailsValue);
                                Log.i("morshed", finalGetImgLink);

                            }
                        });*/

                    } catch (Exception e) {

                    }



                // marked for your use


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


            String finalDes ="";
            for (int i=0; i<description.length; i++){
                finalDes = finalDes+description[i]+"\n\n";
            }


            tvTitle.setText(getTitleValue);
            tvDate.setText(getDateValue);
            tvDetails.setText(finalDes);
            mDetails.setVisibility(View.VISIBLE);

            Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);

            if (finalGetImgLink == null) {
                System.out.println(finalGetImgLink);
//                Log.i("morshed",finalGetImgLink);
             //   Log.i("morshed" + "2nd url is here", finalGetImgLink);
            } else {
                Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);

            }



//            try {
//                Picasso.with(getApplicationContext()).load(finalGetImgLink).into(imgUrl);
//            } catch (Exception e) {
//                Log.i("morshed"+"Get an error---",e.toString());
//            }


//            Log.i("morshed", getTitleValue);
//            Log.i("morshed", getDateValue);
//            Log.i("morshed",getDetailsValue);
//            Log.i("morshed", finalGetImgLink);
        }
    }

    private Context getDialogContext(){
        Context context;
        if(getApplicationContext() != null)
            context = ProthomAloLatestDetailsActivity.this;
        else
            context = ProthomAloLatestDetailsActivity.this;

        return context;
    }

}
