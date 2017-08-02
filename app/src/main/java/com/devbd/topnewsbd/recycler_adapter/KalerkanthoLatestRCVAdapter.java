package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.kalerkantho_model.KalerKanthoLatestModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by morshed on 7/24/17.
 */

public class KalerkanthoLatestRCVAdapter extends RecyclerView.Adapter<KalerkanthoLatestRCVAdapter.KalerKanthoLatestVWHolder> {

    private ArrayList<KalerKanthoLatestModel> latestNews = new ArrayList<>();
    private Context context;
    private KalerkanthoLatestRCVAdapter.OnItemClickListener onItemClickListener;
    private String str ="";


    public KalerkanthoLatestRCVAdapter(Context context,ArrayList<KalerKanthoLatestModel> latestNews) {
        this.context = context;
        this.latestNews = latestNews;
    }


    @Override
    public KalerKanthoLatestVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);
        return new KalerKanthoLatestVWHolder(view);
    }

    @Override
    public void onBindViewHolder(KalerKanthoLatestVWHolder holder, int position) {

        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        holder.mDate.setText(latestNews.get(position).getDate());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public class KalerKanthoLatestVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView newsTitle;
        private TextView mDate;
        private ImageView news_img;


        public KalerKanthoLatestVWHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
            mDate = (TextView) itemView.findViewById(R.id.date);
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

    public void SetOnItemClickListener(final KalerkanthoLatestRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
