package com.devbd.topnewsbd.fragment.fragment_top_news;


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

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.detail_activity.ProthomAloLatestDetailsActivity;
import com.devbd.topnewsbd.detail_activity.TopNewsLatestDetailsActivity;
import com.devbd.topnewsbd.fragment.fragment_prothom_alo.LatestNewsProthomAlo;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.prothomalo_model.ProthomAloLatestModel;
import com.devbd.topnewsbd.model.samakal_model.SamakalLatestModel;
import com.devbd.topnewsbd.model.topnews_model.TopNewsLatestModel;
import com.devbd.topnewsbd.recycler_adapter.BdNewsTopViewsRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.ProthomAloLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.SamakalLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.TopNewsLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsTop extends Fragment {


    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<TopNewsLatestModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private TopNewsLatestRCVAdapter adapter;




    public LatestNewsTop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_latest_news, container, false);


        arrayList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetCheckDialogHelper.isOnline(getContext()) == true){
                    new TopViewsLatestJSOUP().execute();
                }else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }

            }
        });


        linearLayoutManager = new LinearLayoutManager(view.getContext());



        if (NetCheckDialogHelper.isOnline(getContext()) == true){
            new TopViewsLatestJSOUP().execute();
        }else {

            NetCheckDialogHelper.dialogNotConnected(getContext());

        }



        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new TopNewsLatestRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("morshed","Latest start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("morshed","Latest resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("morshed","Latest pause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("morshed","Latest attach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("morshed","Latest detach");
    }


    private class TopViewsLatestJSOUP extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected Boolean doInBackground(Void... params) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://bdexpress.uodoo.com/index/headlines").get();

                simplifiedData = doc.select("div.w-list");

                Elements latestHeading = simplifiedData.select("div.w-list-post-detail > h3");
                Elements mTime = simplifiedData.select("div.w-list-meta > span.w-list-time");
                Elements mNewspaperName = simplifiedData.select("div.w-list-meta > span.w-list-seedname");
                Elements mLink = simplifiedData.select("a.w-list-post");

                for(int i=0; i<mLink.size(); i++){
                    String title = latestHeading.get(i).text();
                    Element imgElement = simplifiedData.select("div > img").get(i);
                    String imgUrl = imgElement.absUrl("src");

                    String time = mTime.get(i).text();
                    String newsPaper = mNewspaperName.get(i).text();
                    String url = mLink.get(i).attr("href");
                    String urlFinal = "http://bdexpress.uodoo.com"+url;



                    TopNewsLatestModel model = new TopNewsLatestModel(title,imgUrl,urlFinal,time,newsPaper);
                    arrayList.add(model);
                    Log.i("morshed",urlFinal+"\n");
//                    Log.i("morshed"+"Url is here: ---",urlFinal);

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


        private void setUpAdapter() {
            adapter = new TopNewsLatestRCVAdapter(arrayList,getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rView.setLayoutManager(linearLayoutManager);
            rView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new TopNewsLatestRCVAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position){
                    Intent intent = new Intent(getContext(), TopNewsLatestDetailsActivity.class);
                    intent.putExtra(Constant.TOPNEWS_LATEST_DETAIL_INFO, arrayList.get(position).getLink());
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

}
