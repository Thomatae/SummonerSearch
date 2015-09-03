package com.league2.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.league2.app.R;

/**
 * Created by Trever on 7/30/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mItemTitles[];
    private int mItemIconIds[];
    private String mSummonerName;
    private int mSummonerIcon;

    private Context mContext;

    public DrawerAdapter(Context context, String[] itemTitles, int[] itemIconIds, String summonerName, int summonerIcon) {
        mContext = context;

        mItemTitles = itemTitles;
        mItemIconIds = itemIconIds;
        mSummonerName = summonerName;
        mSummonerIcon = summonerIcon;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_row, parent, false);

            return new ViewHolder(v,viewType);

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header, parent ,false);

            return new ViewHolder(v,viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(viewHolder.Holderid == 1) {
            viewHolder.itemTitle.setText(mItemTitles[position - 1]);
            viewHolder.itemIcon.setImageResource(mItemIconIds[position -1]);
        }
        else{
            viewHolder.summonerIcon.setImageResource(mSummonerIcon);
            viewHolder.summonerName.setText(mSummonerName);
        }
    }

    @Override
    public int getItemCount() {
        return mItemTitles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView itemTitle;
        ImageView itemIcon;
        ImageView summonerIcon;
        TextView summonerName;


        public ViewHolder(View itemView,int ViewType) {
            super(itemView);

            if(ViewType == TYPE_ITEM) {
                itemTitle = (TextView) itemView.findViewById(R.id.item_title);
                itemIcon = (ImageView) itemView.findViewById(R.id.icon);
                Holderid = 1;
            } else{
                summonerName = (TextView) itemView.findViewById(R.id.summoner_name);
                summonerIcon = (ImageView) itemView.findViewById(R.id.summoner_icon);
                Holderid = 0;
            }
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
