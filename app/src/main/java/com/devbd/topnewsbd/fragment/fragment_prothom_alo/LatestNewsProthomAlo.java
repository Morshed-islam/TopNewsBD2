package com.devbd.topnewsbd.fragment.fragment_prothom_alo;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;
import com.devbd.topnewsbd.detail_activity.ProthomAloLatestDetailsActivity;
import com.devbd.topnewsbd.helper.HelperMethod;
import com.devbd.topnewsbd.helper.NetCheckDialogHelper;
import com.devbd.topnewsbd.model.prothomalo_model.ProthomAloLatestModel;
import com.devbd.topnewsbd.recycler_adapter.ProthomAloLatestRCVAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNewsProthomAlo extends Fragment {

    private String TAG ="morshed";
    private RecyclerView rView;
    private ArrayList<ProthomAloLatestModel> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private ProthomAloLatestRCVAdapter adapter;


    public LatestNewsProthomAlo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_latest_news_prothom_alo, container, false);


        arrayList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetCheckDialogHelper.isOnline(getContext()) == true){
                    new ProthomALoLatestJSOUP().execute();
                }else {

                    NetCheckDialogHelper.dialogNotConnected(getContext());

                }

            }
        });


        linearLayoutManager = new LinearLayoutManager(view.getContext());



        if (NetCheckDialogHelper.isOnline(getContext()) == true){
            new ProthomALoLatestJSOUP().execute();
        }else {

            NetCheckDialogHelper.dialogNotConnected(getContext());

        }


//        if (isOnline() == true){
//            new ProthomALoLatestJSOUP().execute();
//            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(getContext(), "Plz check your Connection!!", Toast.LENGTH_SHORT).show();
//        }










        rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(linearLayoutManager);

        adapter =new ProthomAloLatestRCVAdapter(arrayList,view.getContext());
        rView.setAdapter(adapter);


        return view;

    }

    private class ProthomALoLatestJSOUP extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progressDialog = HelperMethod.getProgressBar(getDialogContext());

        @Override
        protected Boolean doInBackground(Void... params) {

            Document doc;
            Elements simplifiedData;

            try {
                doc = Jsoup.connect("http://www.prothom-alo.com/archive").get();

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



                    ProthomAloLatestModel model = new ProthomAloLatestModel(title,imgUrl,time,urlFinal);
                    arrayList.add(model);
                    Log.i("morshed",title+"\n"+imgUrl+"\n");
                    Log.i("morshed"+"Url is here: ---",urlFinal);

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
        adapter = new ProthomAloLatestRCVAdapter(arrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rView.setLayoutManager(linearLayoutManager);
        rView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new ProthomAloLatestRCVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Intent intent = new Intent(getContext(), ProthomAloLatestDetailsActivity.class);
                intent.putExtra(Constant.PROTHOM_ALO_LATEST_DETAIL_INFO, arrayList.get(position).getLink());
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


//    public boolean isOnline() {
//        ConnectivityManager conMgr = (ConnectivityManager) getDialogContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
//
//        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
//            Toast.makeText(getDialogContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }



    }

//    public boolean isOnline() {
//        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }
//
//
//    public void checkNet(){
//
//        if (isOnline() == true) {
//            new ProthomALoLatestJSOUP().execute();
//        }else {
//            try {
//                Toast.makeText(getContext(), "Check internet", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
//                builder.setTitle("No internet Connection");
//                builder.setMessage("Please turn on internet connection to continue");
//                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//            catch(Exception e)
//            {
//                Log.d("morshed", "Show Dialog: "+e.getMessage());
//            }
//
//
//    }
//}


}
