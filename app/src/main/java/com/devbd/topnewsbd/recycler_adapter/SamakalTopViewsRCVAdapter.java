package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.samakal_model.SamakalTopViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by morshed on 8/1/17.
 */

public class SamakalTopViewsRCVAdapter extends RecyclerView.Adapter<SamakalTopViewsRCVAdapter.SamakalTopViewsVWholder> {

    private ArrayList<SamakalTopViewModel> latestNews = new ArrayList<>();
    private Context context;
    private SamakalTopViewsRCVAdapter.OnItemClickListener onItemClickListener;
    private String str ="";

    public SamakalTopViewsRCVAdapter(ArrayList<SamakalTopViewModel> latestNews, Context context) {
        this.latestNews = latestNews;
        this.context = context;
    }

    @Override
    public SamakalTopViewsVWholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);

        return new SamakalTopViewsVWholder(view);
    }

    @Override
    public void onBindViewHolder(SamakalTopViewsVWholder holder, int position) {
        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        holder.mDate.setText(latestNews.get(position).getDate());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public class SamakalTopViewsVWholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private TextView mDate;
        private ImageView news_img;

        public SamakalTopViewsVWholder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            mDate = (TextView) itemView.findViewById(R.id.date);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
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

    public void SetOnItemClickListener(final SamakalTopViewsRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
