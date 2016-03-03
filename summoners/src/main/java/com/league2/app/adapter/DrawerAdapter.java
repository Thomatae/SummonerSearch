package com.league2.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.league2.app.R;
import com.squareup.picasso.Picasso;

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
    private NavigationListener mNavigationListener;

    private Context mContext;

    public interface NavigationListener {
        void startHome();
        void startSummonerSearch();
        void startChampionSearch();
    }

    private interface OnClickListener {
        void onClick(View view, int position);
    }

    public DrawerAdapter(Context context, NavigationListener listener, String[] itemTitles, int[] itemIconIds, String summonerName, int summonerIcon) {
        mContext = context;
        mNavigationListener = listener;
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
        if(viewHolder.HolderId == TYPE_ITEM) {
            viewHolder.itemTitle.setText(mItemTitles[position - 1]);
            viewHolder.itemIcon.setImageResource(mItemIconIds[position -1]);
        }
        else{
            String formattedURL = String.format(getString(R.string.dragon_url), getString(R.string.profile_icon), mSummonerIcon);
            Picasso.with(mContext).load(formattedURL).into(viewHolder.summonerIcon);
            viewHolder.summonerName.setText(mSummonerName);
        }

        viewHolder.setClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                handleOnClick(mItemTitles[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemTitles.length + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int HolderId;

        TextView itemTitle;
        ImageView itemIcon;
        ImageView summonerIcon;
        TextView summonerName;
        OnClickListener clickListener;


        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            itemView.setOnClickListener(this);

            if(ViewType == TYPE_ITEM) {
                itemView.setOnClickListener(this);
                itemTitle = (TextView) itemView.findViewById(R.id.item_title);
                itemIcon = (ImageView) itemView.findViewById(R.id.icon);
                HolderId = 1;
            } else{
                summonerName = (TextView) itemView.findViewById(R.id.summoner_name);
                summonerIcon = (ImageView) itemView.findViewById(R.id.summoner_icon);
                HolderId = 0;
            }
        }


        @Override
        public void onClick(View v) {
            if (HolderId == 1) {
                clickListener.onClick(v, getPosition() - 1);
            }
        }

        public void setClickListener(OnClickListener listener) {
            clickListener = listener;
        }
    }

    private void handleOnClick(String title) {
        switch (title) {
            case "Home":
                mNavigationListener.startHome();
                break;
            case "Summoner Search":
                mNavigationListener.startSummonerSearch();
                break;
            case "Champions":
                mNavigationListener.startChampionSearch();
                break;
        }
    }

    public String getString(int stringId) {
        return mContext.getResources().getString(stringId);
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
