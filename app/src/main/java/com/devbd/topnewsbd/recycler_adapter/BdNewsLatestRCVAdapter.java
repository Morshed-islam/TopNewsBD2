package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.bdnews_model.BdNewsLatestModel;

import java.util.ArrayList;

/**
 * Created by morshed on 7/24/17.
 */

public class BdNewsLatestRCVAdapter extends RecyclerView.Adapter<BdNewsLatestRCVAdapter.BdNewsLatestVWHolder> {

    private ArrayList<BdNewsLatestModel> latestNews = new ArrayList<>();
    //private ArrayList<BdNewsTopViewModel>  topNews = new ArrayList<>();
    private Context context;
    private BdNewsLatestRCVAdapter.OnItemClickListener onItemClickListener;
    private String str;


    public BdNewsLatestRCVAdapter(Context context, ArrayList<BdNewsLatestModel> latestNews, String str) {
        this.latestNews = latestNews;
        this.context = context;
        this.str = str;
    }

//    public BdNewsLatestRCVAdapter(Context context, ArrayList<BdNewsTopViewModel> topNews) {
//        this.topNews = topNews;
//        this.context = context;
//
//    }

    @Override
    public BdNewsLatestVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);

        return new BdNewsLatestVWHolder(view);
    }

    @Override
    public void onBindViewHolder(BdNewsLatestVWHolder holder, int position) {
        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        //Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);
        holder.news_img.getDrawable();

    }

    @Override
    public int getItemCount() {

        return latestNews.size();
    }

    public class BdNewsLatestVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private ImageView news_img;

        public BdNewsLatestVWHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view , int position);

    }

    public void SetOnItemClickListener(final BdNewsLatestRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
