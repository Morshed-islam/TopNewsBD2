package com.devbd.topnewsbd.fragment.fragment_bdnews;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.model.bdnews_model.BdNewsLatestModel;
import com.devbd.topnewsbd.recycler_adapter.BdNewsLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsBDNews extends Fragment {
    private RecyclerView rView;
    private ArrayList<BdNewsLatestModel> arrayList;
    private BdNewsLatestRCVAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String str ="";
    private SwipeRefreshLayout swipeRefreshLayout;

    private String TAG = "BDNEWS";

    public LatestNewsBDNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_news_bdnews, container, false);

        arrayList = new ArrayList<>();


        //new BdNewsLatestJsoup().execute();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipRefresh);
        //swipeRefreshLayout.setOnRefreshListener();


        linearLayoutManager = new LinearLayoutManager(view.getContext());

        swipeRefreshLayout.setRefreshing(true);
        new BdNewsLatestJsoup().execute();


        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        //adapter = new BdNewsLatestRCVAdapter(view.getContext(), arrayList,str);

        //rView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i(TAG,"On Attached Latest");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"On Resume Latest");
        rView.setAdapter(new BdNewsLatestRCVAdapter(getContext(),arrayList,str));

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"On Pause Latest");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"On DeAttached Latest");
    }

    private class BdNewsLatestJsoup extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
           // HelperMethod.startProgressBar(progressDialog,"Loading..");
            swipeRefreshLayout.setRefreshing(true);
        }

        @SuppressLint("LongLogTag")
        @Override
        protected Boolean doInBackground(Void... params) {

            Document doc;
            Elements elements;


            //get all html code
            try {


                doc = Jsoup.connect("http://bangla.bdnews24.com/").get();


                elements = doc.select("div#other_top_stories_home_css");

                ///T/ODO this is for TopViews data
                //elements = doc.select("div.content");

                Elements latestHeading = elements.select("h6 > a");
                //Elements latestHeading = elements.select("ul > li > a");

                for (int i = 0; i < latestHeading.size(); i++) {

                        String title = latestHeading.get(i).text();
                        //System.out.println(el.text());
                        //System.out.println(el.attr("href"));

                        BdNewsLatestModel bdNewsLatestModel = new BdNewsLatestModel(title);
                        arrayList.add(bdNewsLatestModel);



                    //Log.i(TAG+"Latest Data is here:",title);


                }

                //TODO for swip progress thread
                for (int i=0; i<5; i++){
                    Thread.sleep(1000);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.interrupted();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //super.onPostExecute(aBoolean);
            //HelperMethod.stopProgressBar(progressDialog);
            swipeRefreshLayout.setRefreshing(false);
            setUpAdapter();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


    private void setUpAdapter() {
        adapter = new BdNewsLatestRCVAdapter(getContext(),arrayList,str);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new BdNewsLatestRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
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
            context = this.getContext();

        return context;
    }


}
