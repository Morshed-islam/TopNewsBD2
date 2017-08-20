package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.kalerkantho_model.KalerKanthoTopViewModel;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

/**
 * Created by morshed on 7/28/17.
 */

public class KalerkanthoTopViewsRCVAdapter extends RecyclerView.Adapter<KalerkanthoTopViewsRCVAdapter.KalerkanthoTopViewsVWHolder> {

    private ArrayList<KalerKanthoTopViewModel> latestNews = new ArrayList<>();
    private Context context;
    private KalerkanthoTopViewsRCVAdapter.OnItemClickListener onItemClickListener;


    public KalerkanthoTopViewsRCVAdapter(Context context,ArrayList<KalerKanthoTopViewModel> latestNews) {
        this.context = context;
        this.latestNews = latestNews;
    }

    @Override
    public KalerkanthoTopViewsVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_all,parent,false);
        return new KalerkanthoTopViewsVWHolder(view);
    }

    @Override
    public void onBindViewHolder(KalerkanthoTopViewsVWHolder holder, int position) {

        holder.newsTitle.setText(latestNews.get(position).getNewsHeading());
        holder.mDate.setText(latestNews.get(position).getDate());
        Picasso.with(context).load(latestNews.get(position).getImageLink()).into(holder.news_img);

    }

    @Override
    public int getItemCount() {
        int size = 0;
        try{
            size = latestNews.size();
        }catch (NullPointerException e){

        }
        return size;
    }

    public class KalerkanthoTopViewsVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView newsTitle;
        private TextView mDate;
        private ImageView news_img;


        public KalerkanthoTopViewsVWHolder(View itemView) {
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

    public void SetOnItemClickListener(final KalerkanthoTopViewsRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
