package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.topnews_model.TopNewsLatestModel;
import com.devbd.topnewsbd.model.topnews_model.TopNewsTopViewModel;

import java.util.ArrayList;

/**
 * Created by morshed on 7/24/17.
 */

//android.support.v7.widget.RecyclerView.Adapter<>
public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.TopNewsVWHolder> {

    private ArrayList<TopNewsLatestModel> latestNews = new ArrayList<>();
    private ArrayList<TopNewsTopViewModel>  topNews = new ArrayList<>();
    private Context context;
    private TopNewsAdapter.OnItemClickListener onItemClickListener;

    public TopNewsAdapter(ArrayList<TopNewsLatestModel> latestNews, Context context) {
        this.latestNews = latestNews;
        this.context = context;
    }

    public TopNewsAdapter(ArrayList<TopNewsTopViewModel> topNews, Context context, String str) {
        this.topNews = topNews;
        this.context = context;
    }

    @Override
    public TopNewsVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);
        return new TopNewsAdapter.TopNewsVWHolder(view);

    }

    @Override
    public void onBindViewHolder(TopNewsVWHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TopNewsVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TopNewsVWHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);

    }

    public void SetOnItemClickListener(final TopNewsAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



}
