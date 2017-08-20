package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.topnews_model.TopNewsLatestModel;
import com.devbd.topnewsbd.model.topnews_model.TopNewsTopViewModel;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

/**
 * Created by morshed on 7/24/17.
 */

//android.support.v7.widget.RecyclerView.Adapter<>
public class TopNewsLatestRCVAdapter extends RecyclerView.Adapter<TopNewsLatestRCVAdapter.TopNewsLatestVWHolder> {

    private ArrayList<TopNewsLatestModel> latestNews = new ArrayList<>();
    private Context context;
    private TopNewsLatestRCVAdapter.OnItemClickListener onItemClickListener;

    public TopNewsLatestRCVAdapter(ArrayList<TopNewsLatestModel> latestNews, Context context) {
        this.latestNews = latestNews;
        this.context = context;
    }


    @Override
    public TopNewsLatestVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_top_news,parent,false);
        return new TopNewsLatestVWHolder(view);

    }

    @Override
    public void onBindViewHolder(TopNewsLatestVWHolder holder, int position) {
        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        holder.mDate.setText(latestNews.get(position).getDate());
        holder.mNewsPaperName.setText(latestNews.get(position).getNewsPaperName());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public class TopNewsLatestVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private TextView mNewsPaperName;
        private TextView mDate;
        private ImageView news_img;

        public TopNewsLatestVWHolder(View itemView) {
            super(itemView);

            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            mDate = (TextView) itemView.findViewById(R.id.date);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
            mNewsPaperName = (TextView) itemView.findViewById(R.id.news_paper_name);

            newsTitle.setTypeface(EasyFonts.robotoMedium(context));

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

    public void SetOnItemClickListener(final TopNewsLatestRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


}
