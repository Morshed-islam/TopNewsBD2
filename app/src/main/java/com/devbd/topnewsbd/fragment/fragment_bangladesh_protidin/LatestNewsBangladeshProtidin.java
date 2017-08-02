package com.devbd.topnewsbd.fragment.fragment_bangladesh_protidin;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.fragment.fragment_jugantor.LatestNewsJugantor;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.model.bangladeshprotidin_model.BangladeshProtidinLatestModel;
import com.devbd.topnewsbd.model.jugantor_model.JugantorLatestModel;
import com.devbd.topnewsbd.recycler_adapter.BangladeshProtidinLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.JugantorLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsBangladeshProtidin extends Fragment {

    private RecyclerView rView;
    private ArrayList<BangladeshProtidinLatestModel> arrayList;
    private BangladeshProtidinLatestRCVAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String str ="";


    public LatestNewsBangladeshProtidin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_news_bangladesh_protidin, container, false);

        arrayList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        new BangladeshProtidinLatestJSOUP().execute();

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new BangladeshProtidinLatestRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;
    }


    private class BangladeshProtidinLatestJSOUP extends AsyncTask<Void,Void,Boolean>{
        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());


        @Override
        protected Boolean doInBackground(Void... voids) {


            Document doc;
            Elements simplifiedData;


            //get all html code resources
            try {
                doc = Jsoup.connect("http://www.bd-pratidin.com/").get();

                //it will get the simplifed all html data
                simplifiedData = doc.select("div#content-md");

                Elements latestHeading = simplifiedData.select("a > div > font");

                try {
                    for (int i = 0; i < latestHeading.size(); i++) {

                        //this is for getting news title
                        String title = latestHeading.get(i).text();
                        //this is for getting news image link
                        Element imgElement = simplifiedData.select("img").get(i);
                        // Element ImgElement = simplifiedData.select("img").get(i);

                        String imgUrl = imgElement.absUrl("src");

                        //this is for getting news date and time
//                        String date = mDate.get(i).text();


                        BangladeshProtidinLatestModel model = new BangladeshProtidinLatestModel(title,imgUrl);
                        arrayList.add(model);

                        // Log.i("morshed",title+"\n"+imgUrl+"\n"+date);

                    }

                }catch (Exception e){
                    e.printStackTrace();
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
            adapter = new BangladeshProtidinLatestRCVAdapter(arrayList,getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rView.setLayoutManager(linearLayoutManager);
            rView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new BangladeshProtidinLatestRCVAdapter.OnItemClickListener() {
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
