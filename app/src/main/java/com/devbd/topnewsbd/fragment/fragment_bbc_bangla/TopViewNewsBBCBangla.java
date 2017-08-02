package com.devbd.topnewsbd.fragment.fragment_bbc_bangla;


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
import com.devbd.topnewsbd.model.bbcbangla_model.BBCBanglaLatestModel;
import com.devbd.topnewsbd.model.bbcbangla_model.BBCBanglaTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.BBCBanglaLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.BBCBanglaTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsBBCBangla extends Fragment {

    private RecyclerView rView;
    private ArrayList<BBCBanglaTopViewModel> arrayList;
    private BBCBanglaTopViewsRCVAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String str ="";



    public TopViewNewsBBCBangla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_view_news_bbcbangla, container, false);

        arrayList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        new BBCBanglaTopJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new BBCBanglaTopViewsRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);

        return view;
    }

    private class BBCBanglaTopJSOUP extends AsyncTask<Void,Void,Boolean>{
        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected Boolean doInBackground(Void... params) {


            Document doc;
            Elements simplifiedData;


            //get all html code resources
            try {
                doc = Jsoup.connect("http://www.bbc.com/bengali/").get();

                //it will get the simplifed all html data
                simplifiedData = doc.select("div#comp-other-news-1");

                Elements latestHeading = simplifiedData.select("h3 > span");
                Elements mDate = simplifiedData.select("ul > li");

                for(int i = 0; i<latestHeading.size(); i++){

                    //this is for getting news title
                    String title = latestHeading.get(i).text();
                    //this is for getting news image link
                    //Element imgElement = simplifiedData.select("").get(i);
                    Element ImgElement = simplifiedData.select("div.js-delayed-image-load").get(i);
                    String imgUrl = ImgElement.absUrl("data-src");

                    //this is for getting news date and time
                    String date = mDate.get(i).text();


                    BBCBanglaTopViewModel model = new BBCBanglaTopViewModel(title,imgUrl,date);
                    arrayList.add(model);

                    Log.i("morshed",title+"\n"+imgUrl+"\n"+date);

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
            adapter = new BBCBanglaTopViewsRCVAdapter(arrayList,getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rView.setLayoutManager(linearLayoutManager);
            rView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new BBCBanglaTopViewsRCVAdapter.OnItemClickListener() {
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

}
