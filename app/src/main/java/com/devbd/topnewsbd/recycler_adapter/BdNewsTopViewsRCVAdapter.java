package com.devbd.topnewsbd.recycler_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.model.bdnews_model.BdNewsTopViewModel;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

/**
 * Created by morshed on 7/26/17.
 */

public class BdNewsTopViewsRCVAdapter extends RecyclerView.Adapter<BdNewsTopViewsRCVAdapter.BdNewsTopViewsVWHolder> {
    private ArrayList<BdNewsTopViewModel> topNEws = new ArrayList<>();
    private Context context;
    private BdNewsTopViewsRCVAdapter.OnItemClickListener onItemClickListener;
    private String str;
    private int customImage = R.drawable.bdnews;



    public BdNewsTopViewsRCVAdapter(Context context, ArrayList<BdNewsTopViewModel> topNEws) {
        this.context = context;
        this.topNEws = topNEws;
    }

    @Override
    public BdNewsTopViewsRCVAdapter.BdNewsTopViewsVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout_with_img_title,parent,false);

        return new BdNewsTopViewsRCVAdapter.BdNewsTopViewsVWHolder(view);

    }

    @Override
    public void onBindViewHolder(BdNewsTopViewsRCVAdapter.BdNewsTopViewsVWHolder holder, int position) {
        holder.newsTitle.setText(topNEws.get(position).getNewsHeading());
        holder.news_img.setImageResource(customImage);
    }

    @Override
    public int getItemCount() {
        return topNEws.size();
    }

    public class BdNewsTopViewsVWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView news_img;
        private TextView newsTitle;

        public BdNewsTopViewsVWHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_heading);
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

    public void SetOnItemClickListener(final BdNewsTopViewsRCVAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
