package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.bd24live_model.Bd24LiveTopViewModel;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

/**
 * Created by morshed on 8/2/17.
 */

public class Bd24TopViewsRCVAdapter extends RecyclerView.Adapter<Bd24TopViewsRCVAdapter.Bd24TopViewsVWHolder> {

    private ArrayList<Bd24LiveTopViewModel> latestNews = new ArrayList<>();
    private Context context;
    private Bd24TopViewsRCVAdapter.OnItemClickListener onItemClickListener;
    private String str ="";


    public Bd24TopViewsRCVAdapter(ArrayList<Bd24LiveTopViewModel> latestNews, Context context) {
        this.latestNews = latestNews;
        this.context = context;
    }

    @Override
    public Bd24TopViewsVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);
        return new Bd24TopViewsVWHolder(view);
    }

    @Override
    public void onBindViewHolder(Bd24TopViewsVWHolder holder, int position) {
        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        holder.mDate.setText(latestNews.get(position).getDate());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public class Bd24TopViewsVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private TextView mDate;
        private ImageView news_img;



        public Bd24TopViewsVWHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            mDate = (TextView) itemView.findViewById(R.id.date);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
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

    public void SetOnItemClickListener(final Bd24TopViewsRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}