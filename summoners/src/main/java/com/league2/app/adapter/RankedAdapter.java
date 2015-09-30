package com.league2.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.league2.app.R;
import com.league2.app.Vo.LeagueVo;
import com.league2.app.Vo.SummonerLeagueStatsVo;

import java.util.List;

/**
 * Created by trethoma1 on 9/29/15.
 */
public class RankedAdapter extends RecyclerView.Adapter<RankedAdapter.ViewHolder> {

    private Context mContext;
    private List<SummonerLeagueStatsVo> mSummonerLeagueStatsVos;

    public RankedAdapter(Context context, List<SummonerLeagueStatsVo> leagueStatsVos) {
        mContext = context;
        mSummonerLeagueStatsVos = leagueStatsVos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranked_card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Each SummonerLeagueVo is for one version of ranked
        SummonerLeagueStatsVo tierInfo = mSummonerLeagueStatsVos.get(position);
        LeagueVo leagueVo = tierInfo.getResults().get(0); //League VO is the specifics for that league

        holder.header.setBackgroundColor(getHeaderColor(tierInfo.tier));
        holder.queue.setText(stripString(tierInfo.queue));
        holder.division.setText(tierInfo.tier + " " + leagueVo.division);
        holder.leaguePointsValue.setText(Integer.toString(leagueVo.leaguePoints));
        holder.winsCount.setText(Integer.toString(leagueVo.wins));
        holder.lossesCount.setText(Integer.toString(leagueVo.losses));
        holder.freshBlood.setTextColor(getVariableColor(leagueVo.isFreshBlood));
        holder.veteran.setTextColor(getVariableColor(leagueVo.isVeteran));
        holder.hotStreak.setTextColor(getVariableColor(leagueVo.isHotStreak));
        holder.inactive.setTextColor(getVariableColor(leagueVo.isInactive));
    }

    @Override
    public int getItemCount() {
        return mSummonerLeagueStatsVos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout header;
        TextView queue;
        TextView division;
        TextView leaguePointsValue;
        TextView winsCount;
        TextView lossesCount;
        TextView freshBlood;
        TextView veteran;
        TextView hotStreak;
        TextView inactive;


        public ViewHolder(View itemView) {
            super(itemView);

            header = (LinearLayout) itemView.findViewById(R.id.header);
            queue = (TextView) itemView.findViewById(R.id.queue);
            division = (TextView) itemView.findViewById(R.id.division);
            leaguePointsValue = (TextView) itemView.findViewById(R.id.league_points_value);
            winsCount = (TextView) itemView.findViewById(R.id.wins_count);
            lossesCount = (TextView) itemView.findViewById(R.id.losses_count);
            freshBlood = (TextView) itemView.findViewById(R.id.fresh_blood);
            veteran = (TextView) itemView.findViewById(R.id.veteran);
            hotStreak = (TextView) itemView.findViewById(R.id.hot_streak);
            inactive = (TextView) itemView.findViewById(R.id.inactive);
        }
    }

    private String stripString(String queue) {
        return queue.replace('_',' ');
    }

    private int getVariableColor(boolean active) {
        if (active) {
            return R.color.disabled_gray;
        } else {
            return R.color.enabled_black;
        }
    }

    private int getColor(int resourceId) {
        return mContext.getResources().getColor(resourceId);
    }

    private int getHeaderColor(String tier) {
        switch (tier) {
            case "CHALLENGER":
                return getColor(R.color.challenger);
            case "DIAMOND":
                return getColor(R.color.diamond);
            case "PLATINUM":
                return getColor(R.color.platinum);
            case "GOLD":
                return getColor(R.color.gold);
            case "SILVER":
                return getColor(R.color.silver);
            case "BRONZE":
                return getColor(R.color.bronze);
            default:
                return getColor(R.color.white);
        }
    }
}
