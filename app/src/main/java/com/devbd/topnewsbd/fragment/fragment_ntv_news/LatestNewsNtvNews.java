package com.devbd.topnewsbd.fragment.fragment_ntv_news;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.detail_activity.NtvNewsLatestDetailsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.dmpnews_model.NtvNewsLatestModel;
import com.devbd.topnewsbd.recycler_adapter.NtvNewsLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsNtvNews extends Fragment {


    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<NtvNewsLatestModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private NtvNewsLatestRCVAdapter adapter;



    public LatestNewsNtvNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_news_dmpnews, container, false);

        arrayList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetCheckDialogHelper.isOnline(getContext()) == true){

                    new NtvLatestJSOUP().execute();
                }else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(view.getContext());

        //this is for checking internet connectiom
        if (NetCheckDialogHelper.isOnline(getContext()) == true){
                new NtvLatestJSOUP().execute();
        }else {

            NetCheckDialogHelper.dialogNotConnected(getContext());

        }



        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new NtvNewsLatestRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;

    }


    private class NtvLatestJSOUP extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());


        @Override
        protected Boolean doInBackground(Void... voids) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://www.ntvbd.com/all-news").get();

                simplifiedData = doc.select("div#main_content_list");

                Elements latestHeading = simplifiedData.select("div.hl > h4");
                Elements mTime = simplifiedData.select("div.post_date > p");

                Elements mlink = simplifiedData.select("div.all_news_content_block > a");


                try {


                    for(int i=0; i<latestHeading.size(); i++){
                        String title = latestHeading.get(i).text();
                        Element imgElement = simplifiedData.select("div.img > img").get(i);
                        String imgUrl = imgElement.absUrl("src");

                        String time = mTime.get(i).text();

                        String link= mlink.get(i).attr("href");
                        Log.i("morshed",link);


                        NtvNewsLatestModel model = new NtvNewsLatestModel(title,imgUrl,time,link);
                        arrayList.add(model);
//                        Log.i("morshed",title+"\n"+imgUrl+"\n");

                    }

                }catch (Exception e){

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

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            HelperMethod.stopProgressBar(progressDialog);
            setUpAdapter();
        }
    }

    private void setUpAdapter() {
        adapter = new NtvNewsLatestRCVAdapter(arrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new NtvNewsLatestRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Intent intent = new Intent(getContext(), NtvNewsLatestDetailsActivity.class);
                intent.putExtra(Constant.NTVNEWS_LATEST_DETAIL_INFO, arrayList.get(position).getLink());
                startActivity(intent);
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private Context getDialogContext(){
        Context context;
        if(getActivity() != null)
            context = getActivity();
        else
            context = getContext();

        return context;
    }




}
