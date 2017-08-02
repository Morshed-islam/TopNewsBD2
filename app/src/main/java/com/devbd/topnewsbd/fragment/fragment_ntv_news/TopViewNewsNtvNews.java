package com.devbd.topnewsbd.fragment.fragment_ntv_news;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.model.dmpnews_model.NtvNewsLatestModel;
import com.devbd.topnewsbd.model.dmpnews_model.NtvNewsTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.NtvNewsLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.NtvNewsTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsNtvNews extends Fragment {


    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<NtvNewsTopViewModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private NtvNewsTopViewsRCVAdapter adapter;



    public TopViewNewsNtvNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_view_news_dmpnews, container, false);

        arrayList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        new NtvTopJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new NtvNewsTopViewsRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);
        return view;

    }

    private class NtvTopJSOUP extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());


        @Override
        protected Boolean doInBackground(Void... voids) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://www.ntvbd.com/all-news/selected-news").get();

                simplifiedData = doc.select("div#main_content_list");

                Elements latestHeading = simplifiedData.select("div.hl > h4");
                Elements mTime = simplifiedData.select("div.post_date > p");


                try {

                    for(int i=0; i<latestHeading.size(); i++){
                        String title = latestHeading.get(i).text();
                        Element imgElement = simplifiedData.select("div.img > img").get(i);
                        String imgUrl = imgElement.absUrl("src");

                        String time = mTime.get(i).text();


                        NtvNewsTopViewModel model = new NtvNewsTopViewModel(title,imgUrl,time);
                        arrayList.add(model);
                        Log.i("morshed",title+"\n"+imgUrl+"\n");

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
        adapter = new NtvNewsTopViewsRCVAdapter(arrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new NtvNewsTopViewsRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
//                Intent intent = new Intent(getContext(), BlogWebDetails.class);
//                intent.putExtra(SyncStateContract.Constants.BLOG_DETAIL_INFO, arrayList.get(position).getLink());
//                startActivity(intent);
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
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
