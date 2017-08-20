package com.devbd.topnewsbd.fragment.fragment_jugantor;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.detail_activity.JugantorTopDetailsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.model.jugantor_model.JugantorTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.JugantorTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsJugantor extends Fragment {


    private RecyclerView rView;
    private ArrayList<JugantorTopViewModel> arrayList;
    private JugantorTopViewsRCVAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String str ="";


    public TopViewNewsJugantor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_view_news_jugantor, container, false);


        arrayList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new JugantorTopJSOUP().execute();
            }
        });

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        new JugantorTopJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new JugantorTopViewsRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;
    }

    private class JugantorTopJSOUP extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected Boolean doInBackground(Void... params) {



            Document doc;
            Elements simplifiedData;


            //get all html code resources
            try {
                doc = Jsoup.connect("http://www.jugantor.com/").get();

                //it will get the simplifed all html data
                simplifiedData = doc.select("div.tabs-content-wrap02");

                Elements latestHeading = simplifiedData.select("li > a");
               // Elements mDate = simplifiedData.select("span#news_publish_time");
               // Elements ImgElement = simplifiedData.select("img");

                for(int i = 0; i<latestHeading.size(); i++){

                    //this is for getting news title
                    String title = latestHeading.get(i).text();
                    //this is for getting news image link
                    //Element imgElement = simplifiedData.select("").get(i);
                    // Element ImgElement = simplifiedData.select("img").get(i);

                    //String imgUrl = ImgElement.get(i).absUrl("src");

                    //this is for getting news date and time
                   // String date = mDate.get(i).text();

                    //link
                    String link = latestHeading.get(i).attr("href");


                    JugantorTopViewModel model = new JugantorTopViewModel(title,link);
                    arrayList.add(model);

                   // Log.i("morshed",title+"\n"+imgUrl+"\n"+date);

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
            adapter = new JugantorTopViewsRCVAdapter(arrayList,getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rView.setLayoutManager(linearLayoutManager);
            rView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new JugantorTopViewsRCVAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position){
                Intent intent = new Intent(getContext(), JugantorTopDetailsActivity.class);
                intent.putExtra(Constant.JUGANTOR_TOP_DETAIL_INFO, arrayList.get(position).getLink());
                startActivity(intent);
//                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
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

