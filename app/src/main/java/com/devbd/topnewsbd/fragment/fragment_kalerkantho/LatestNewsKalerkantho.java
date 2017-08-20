package com.devbd.topnewsbd.fragment.fragment_kalerkantho;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.devbd.topnewsbd.detail_activity.KalerkanthoLatestDeatilsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.kalerkantho_model.KalerKanthoLatestModel;
import com.devbd.topnewsbd.recycler_adapter.KalerkanthoLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsKalerkantho extends Fragment {

    private String TAG = "morshed";
    private RecyclerView rView;
    private ArrayList<KalerKanthoLatestModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private KalerkanthoLatestRCVAdapter adapter;


    public LatestNewsKalerkantho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_news_kalerkantho, container, false);


        arrayList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this is for checking internet connectiom
                if (NetCheckDialogHelper.isOnline(getContext()) == true) {
                    new KalerkanthoLatestJSOUP().execute();

                } else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(view.getContext());

//this is for checking internet connectiom
        if (NetCheckDialogHelper.isOnline(getContext()) == true){
            new KalerkanthoLatestJSOUP().execute();

        }else {

            NetCheckDialogHelper.dialogNotConnected(getContext());

        }

        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter = new KalerkanthoLatestRCVAdapter(view.getContext(), arrayList);
        rView.setAdapter(adapter);


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

        Log.i(TAG, "On Attached Latest");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume Latest");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause Latest");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "On DeAttached Latest");
    }


    public class KalerkanthoLatestJSOUP extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());


        @Override
        protected Boolean doInBackground(Void... params) {
            Document doc;
            Elements simplifiedData;


            //get all html code resources
            try {
                doc = Jsoup.connect("http://www.kalerkantho.com/").get();

                //it will get the simplifed all html data
                simplifiedData = doc.select("div.content");

                Elements latestHeading = simplifiedData.select("li > a");
                Elements mDate = simplifiedData.select("li > small");
                Elements mLink = simplifiedData.select("li > a");


                for (int i = 0; i < 10; i++) {

                    //this is for getting news title
                    String title = latestHeading.get(i).text();
                    //this is for getting news image link
                    Element imgElement = simplifiedData.select("img").get(i);
                    String imgUrl = imgElement.absUrl("src");

                    //this is for getting news date and time
                    String date = mDate.get(i).text();

                    //for getting link
                    String link = mLink.get(i).absUrl("href");
                    Log.i("morshed" + "Link is here", link);

//                    String finalLink = "http://www.kalerkantho.com/"+link;
//                    Log.i("morshed"+"final Link is here",finalLink);

                    KalerKanthoLatestModel model = new KalerKanthoLatestModel(title, imgUrl, date, link);
                    arrayList.add(model);

                    Log.i("morshed", title + "\n" + imgUrl + "\n" + date);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HelperMethod.startProgressBar(progressDialog, "Loading..");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            HelperMethod.stopProgressBar(progressDialog);
            setUpAdapter();
        }


        private void setUpAdapter() {
            adapter = new KalerkanthoLatestRCVAdapter(getContext(), arrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rView.setLayoutManager(linearLayoutManager);
            rView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new KalerkanthoLatestRCVAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getContext(), KalerkanthoLatestDeatilsActivity.class);
                    intent.putExtra(Constant.KALER_KANTHO_LATEST_DETAIL_INFO, arrayList.get(position).getLink());
                    startActivity(intent);
//               Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }


        private Context getDialogContext() {
            Context context;
            if (getActivity() != null)
                context = getActivity();
            else
                context = this.getDialogContext();

            return context;
        }
    }

}
