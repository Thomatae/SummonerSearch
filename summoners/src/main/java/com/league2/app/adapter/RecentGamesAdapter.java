package com.league2.app.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.league2.app.R;
import com.league2.app.vo.GameStatsVo;
import com.league2.app.vo.GameVo;

import java.util.List;

public class RecentGamesAdapter extends BaseArrayAdapter<GameVo> {

    private final LayoutInflater mInflater;
    private List<GameVo> mRecentGames;

    public RecentGamesAdapter(Context context, List<GameVo> recentGames) {
        super(context, 0, recentGames);

        mRecentGames = recentGames;

        mInflater = LayoutInflater.from(context);
    }

    private static class ViewHolder {
        TextView championName;
        TextView killsDeathsAssists;
        TextView date;
        TextView gameMode;
        TextView minionKills;
        TextView goldEarned;
        ImageView championIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.row_recent_games, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.championName = (TextView) convertView.findViewById(R.id.champion_name);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.killsDeathsAssists = (TextView) convertView.findViewById(R.id.kills_death_assists);
            viewHolder.gameMode = (TextView) convertView.findViewById(R.id.game_mode);
            viewHolder.minionKills = (TextView) convertView.findViewById(R.id.minionKills);
            viewHolder.goldEarned = (TextView) convertView.findViewById(R.id.goldEarned);
            viewHolder.championIcon = (ImageView) convertView.findViewById(R.id.champion_icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GameVo game = mRecentGames.get(position);
        GameStatsVo stats = game.getStats();

        viewHolder.gameMode.setText(game.gameMode);
        viewHolder.killsDeathsAssists.setText(stats.championsKilled + "/" + stats.numDeaths + "/" + stats.assists);
        viewHolder.minionKills.setText("CS: " + stats.minionsKilled);
        viewHolder.goldEarned.setText("Gold Earned: " + stats.goldEarned);


        return convertView;

    }
}
