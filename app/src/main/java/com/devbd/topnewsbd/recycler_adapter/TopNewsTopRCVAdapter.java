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
 * Created by morshed on 8/18/17.
 */

public class TopNewsTopRCVAdapter extends RecyclerView.Adapter<TopNewsTopRCVAdapter.TopNewsTopVWHolder> {

    private ArrayList<TopNewsTopViewModel>  topNews = new ArrayList<>();
    private Context context;
    private TopNewsTopRCVAdapter.OnItemClickListener onItemClickListener;


    public TopNewsTopRCVAdapter(ArrayList<TopNewsTopViewModel> topNews, Context context) {
        this.topNews = topNews;
        this.context = context;
    }

    @Override
    public TopNewsTopVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_top_news,parent,false);
        return new TopNewsTopVWHolder(view);
    }

    @Override
    public void onBindViewHolder(TopNewsTopVWHolder holder, int position) {
        holder.newsTitle.setText(topNews.get(position).getNewsHeading());
        holder.mDate.setText(topNews.get(position).getDate());
        holder.mNewsPaperName.setText(topNews.get(position).getNewsPaperName());
        Picasso.with(context).load(topNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return topNews.size();
    }

    public class TopNewsTopVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView newsTitle;
        private TextView mNewsPaperName;
        private TextView mDate;
        private ImageView news_img;

        public TopNewsTopVWHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            mDate = (TextView) itemView.findViewById(R.id.date);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
            mNewsPaperName = (TextView) itemView.findViewById(R.id.news_paper_name);

            newsTitle.setTypeface(EasyFonts.robotoMedium(context));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);

    }

    public void SetOnItemClickListener(final TopNewsTopRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
