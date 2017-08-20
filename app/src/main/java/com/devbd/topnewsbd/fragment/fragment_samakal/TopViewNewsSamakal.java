package com.devbd.topnewsbd.fragment.fragment_samakal;


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
import com.devbd.topnewsbd.detail_activity.SamakalTopDetailsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.samakal_model.SamakalTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.SamakalTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsSamakal extends Fragment {

    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<SamakalTopViewModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private SamakalTopViewsRCVAdapter adapter;


    public TopViewNewsSamakal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_view_news_samakal, container, false);
        arrayList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetCheckDialogHelper.isOnline(getContext()) == true){
                    new SamakalTopJSOUP().execute();

                }else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        new SamakalTopJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new SamakalTopViewsRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;

    }


    private class SamakalTopJSOUP extends AsyncTask<Void,Void,Boolean>{
        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());


        @Override
        protected Boolean doInBackground(Void... voids) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://bangla.samakal.net/").get();

                simplifiedData = doc.select("div#most_view_content");

                Elements latestHeading = simplifiedData.select("a > font");

                Elements mLink = simplifiedData.select("ul > li > a");
                Log.i("morshed",mLink.attr("href"));



                try {

                    for(int i=0; i<latestHeading.size(); i++){
                        String title = latestHeading.get(i).text();
                        Element imgElement = simplifiedData.select("img").get(i);
                        String imgUrl = imgElement.absUrl("src");

                        String link = mLink.get(i).attr("href");
                        Log.i("morshed",link);

                        SamakalTopViewModel model = new SamakalTopViewModel(title,imgUrl,link);
                        arrayList.add(model);
//                        Log.i("morshed",title+"\n"+imgUrl);

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
        adapter = new SamakalTopViewsRCVAdapter(arrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new SamakalTopViewsRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Intent intent = new Intent(getContext(), SamakalTopDetailsActivity.class);
                intent.putExtra(Constant.SAMAKAL_TOP_DETAIL_INFO, arrayList.get(position).getLink());
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
