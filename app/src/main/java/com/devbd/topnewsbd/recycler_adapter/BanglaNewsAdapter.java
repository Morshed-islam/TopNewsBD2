package com.devbd.topnewsbd.recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by morshed on 7/24/17.
 */

public class BanglaNewsAdapter extends RecyclerView.Adapter<BanglaNewsAdapter.BanglaNewsVWHolder> {
    @Override
    public BanglaNewsVWHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BanglaNewsVWHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BanglaNewsVWHolder extends RecyclerView.ViewHolder {
        public BanglaNewsVWHolder(View itemView) {
            super(itemView);
        }
    }
}
