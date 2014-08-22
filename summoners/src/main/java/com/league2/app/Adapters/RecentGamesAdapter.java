package com.league2.app.Adapters;


import android.content.Context;
import android.support.v4.view.ViewCompatKitKat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.league2.app.R;
import com.league2.app.Vo.GameVo;
import com.league2.app.Vo.RecentGamesVo;

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
        TextView gameMode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.row_recent_games, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.gameMode = (TextView) convertView.findViewById(R.id.game_mode);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GameVo game = mRecentGames.get(position);

        viewHolder.gameMode.setText(game.gameMode);

        return convertView;

    }
}
