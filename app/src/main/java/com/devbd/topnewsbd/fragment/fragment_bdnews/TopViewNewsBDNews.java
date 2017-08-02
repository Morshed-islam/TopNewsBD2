package com.devbd.topnewsbd.fragment.fragment_bdnews;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.devbd.topnewsbd.model.bdnews_model.BdNewsLatestModel;
import com.devbd.topnewsbd.model.bdnews_model.BdNewsTopViewModel;
import com.devbd.topnewsbd.recycler_adapter.BdNewsLatestRCVAdapter;
import com.devbd.topnewsbd.recycler_adapter.BdNewsTopViewsRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopViewNewsBDNews extends Fragment {
    private RecyclerView rView;
    private ArrayList<BdNewsTopViewModel> arrayList;
    private BdNewsTopViewsRCVAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private String TAG = "BDNEWS";
    private String str ="";


    public TopViewNewsBDNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_view_news_bdnews, container, false);

        arrayList = new ArrayList<>();

//        Button btn = (Button) view.findViewById(R.id.refresh);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new BdNewsTopJsoup().execute();
//            }
//        });




        linearLayoutManager = new LinearLayoutManager(view.getContext());

        rView = (RecyclerView) view.findViewById(R.id.recycler_view_top);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter = new BdNewsTopViewsRCVAdapter(view.getContext(), arrayList);

        rView.setAdapter(adapter);

        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        new BdNewsTopJsoup().execute();
        Log.i(TAG,"On Attached Top");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"On DeAttached To");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"On Resume top");


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"On Pause top");
    }

    private class BdNewsTopJsoup extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());
        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            HelperMethod.startProgressBar(progressDialog,"Loading..");
        }

        @Override
        protected Boolean doInBackground(Void... params) {


            Document doc;
            Elements elements;


            //get all html code
            try {
                doc = Jsoup.connect("http://bangla.bdnews24.com/").get();


                elements = doc.select("div.content");
                //T/ODO this is for Latest data
                //elements = doc.select("div#other_top_stories_home_css");

                Elements topHeading = elements.select("ul > li > a");
               // Elements topHeading = elements.select("h6 > a");


                for (int i = 0; i < topHeading.size(); i++) {

                    String title = topHeading.get(i).text();
                    //System.out.println(el.text());
                    //System.out.println(el.attr("href"));

//                    BdNewsTopViewModel bdNewsTopViewModel = new BdNewsTopViewModel(title);
//                    arrayList.add(bdNewsTopViewModel);
                    BdNewsTopViewModel model = new BdNewsTopViewModel(title);
                    arrayList.add(model);

                    Log.i(TAG+"Top Data is here: --",title);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //super.onPostExecute(aBoolean);
            HelperMethod.stopProgressBar(progressDialog);
            setUpAdapter();
        }
    }



    private void setUpAdapter() {
        adapter = new BdNewsTopViewsRCVAdapter(getContext(),arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new BdNewsTopViewsRCVAdapter.OnItemClickListener() {
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
