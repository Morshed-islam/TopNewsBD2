package com.devbd.topnewsbd.fragment.fragment_prothom_alo;


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
import com.devbd.topnewsbd.detail_activity.ProthomAloTopDetailsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.prothomalo_model.ProthomAloTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.ProthomAloTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsProthomAlo extends Fragment {

    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<ProthomAloTopViewModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private ProthomAloTopViewsRCVAdapter adapter;


    public TopViewNewsProthomAlo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_news_views_prothom_alo, container, false);

        arrayList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (NetCheckDialogHelper.isOnline(getContext()) == true){
                    new ProthomALoTopJSOUP().execute();
                }else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(view.getContext());


        new ProthomALoTopJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new ProthomAloTopViewsRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);



        return view;
    }


    private class ProthomALoTopJSOUP extends AsyncTask<Void,Void,Boolean>{


        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected Boolean doInBackground(Void... params) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://www.prothom-alo.com/home/most-viewed").get();

                simplifiedData = doc.select("div.contents");

                Elements latestHeading = simplifiedData.select("div.info > h2.title_holder > span.title");
                Elements mTime = simplifiedData.select("div.info > div.additional > span.time");
                Elements mLink = simplifiedData.select("div.each > a");

                for(int i=0; i<10; i++){
                    String title = latestHeading.get(i).text();
                    Element imgElement = simplifiedData.select("img").get(i);
                    String imgUrl = imgElement.absUrl("src");

                    String time = mTime.get(i).text();
                    String url = mLink.get(i).attr("href");
                    String urlFinal = "http://www.prothom-alo.com/"+url;

                    ProthomAloTopViewModel model = new ProthomAloTopViewModel(title,imgUrl,time,urlFinal);
                    arrayList.add(model);
                    Log.i("morshed",title+"\n"+imgUrl+"\n");

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
        adapter = new ProthomAloTopViewsRCVAdapter(arrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new ProthomAloTopViewsRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Intent intent = new Intent(getContext(), ProthomAloTopDetailsActivity.class);
                intent.putExtra(Constant.PROTHOM_ALO_TOP_DETAIL_INFO, arrayList.get(position).getLink());
                startActivity(intent);

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
