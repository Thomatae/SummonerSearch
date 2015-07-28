package com.league2.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.league2.app.R;
import com.league2.app.vo.LeagueVo;
import com.league2.app.vo.SummonerLeagueStatsVo;

import java.util.List;

public class LeaguesAdapter extends BaseArrayAdapter<SummonerLeagueStatsVo> {

    private final LayoutInflater mLayoutInflater;
    private List<SummonerLeagueStatsVo> mSummonerLeagueInfo;

    public LeaguesAdapter(Context context, List<SummonerLeagueStatsVo> summonerLeagueInfo) {
        super(context, 0, summonerLeagueInfo);

        mSummonerLeagueInfo = summonerLeagueInfo;

        mLayoutInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder {
        private TextView currentRank;
        private TextView leaguePoints;
        private TextView seriesProgress;
        private TextView nameOfLeague;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.general_info_row, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.currentRank = (TextView) convertView.findViewById(R.id.current_rank);
            viewHolder.leaguePoints = (TextView) convertView.findViewById(R.id.league_points);
            viewHolder.seriesProgress = (TextView) convertView.findViewById(R.id.progress);
            viewHolder.nameOfLeague = (TextView) convertView.findViewById(R.id.name_of_league);

            convertView.setTag(viewHolder);
        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SummonerLeagueStatsVo stats = mSummonerLeagueInfo.get(position);
        LeagueVo league = stats.getResutls();

        viewHolder.currentRank.setText(stats.tier + " " + league.division);
        viewHolder.leaguePoints.setText(Integer.toString(league.leaguePoints));

        if (league.getSeries() != null) {
            viewHolder.seriesProgress.setText(league.getSeries().progress);
        } else {
            viewHolder.seriesProgress.setText(R.string.not_available);
        }

        viewHolder.nameOfLeague.setText(stats.name);


        return convertView;
    }
}
