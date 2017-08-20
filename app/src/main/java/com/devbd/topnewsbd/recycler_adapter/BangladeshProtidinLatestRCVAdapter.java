package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.bangladeshprotidin_model.BangladeshProtidinLatestModel;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

/**
 * Created by morshed on 7/24/17.
 */

public class BangladeshProtidinLatestRCVAdapter extends RecyclerView.Adapter<BangladeshProtidinLatestRCVAdapter.BangladeshProtidinVWHolder> {

    private ArrayList<BangladeshProtidinLatestModel> latestNews = new ArrayList<>();
    private Context context;
    private BangladeshProtidinLatestRCVAdapter.OnItemClickListener onItemClickListener;
    private String str ="";

    public BangladeshProtidinLatestRCVAdapter(ArrayList<BangladeshProtidinLatestModel> latestNews, Context context) {
        this.latestNews = latestNews;
        this.context = context;
    }

    @Override
    public BangladeshProtidinVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_img_title,parent,false);
        return new BangladeshProtidinVWHolder(view);

    }

    @Override
    public void onBindViewHolder(BangladeshProtidinVWHolder holder, int position) {
        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
//        holder.mDate.setText(latestNews.get(position).getDate());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }



    public class BangladeshProtidinVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private TextView mDate;
        private ImageView news_img;

        public BangladeshProtidinVWHolder(View itemView) {
            super(itemView);

            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
//            mDate = (TextView) itemView.findViewById(R.id.date);
            news_img = (ImageView) itemView.findViewById(R.id.news_imgage);
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

    public void SetOnItemClickListener(final BangladeshProtidinLatestRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
