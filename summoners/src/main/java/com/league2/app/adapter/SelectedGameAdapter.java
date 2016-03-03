package com.league2.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.league2.app.Module.ChampionsVo;
import com.league2.app.R;
import com.league2.app.Vo.MatchDetailVo;
import com.league2.app.Vo.Participant;
import com.league2.app.Vo.ParticipantStatsVo;
import com.league2.app.Vo.PlayerVo;
import com.league2.app.Vo.SummonerSpellsVo;
import com.league2.app.Vo.TeamVo;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by trethoma1 on 2/16/16.
 */
public class SelectedGameAdapter extends RecyclerView.Adapter<SelectedGameAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private MatchDetailVo mMatchDetailVo;
    private ChampionsVo mChampions;
    private SummonerSpellsVo mSummonerSpellsVo;
    private SimpleDateFormat mDateFormatter;

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    public SelectedGameAdapter(Context context, MatchDetailVo match, ChampionsVo champions, SummonerSpellsVo summonerSpellsVo) {
        mContext = context;
        mMatchDetailVo = match;
        mChampions = champions;
        mSummonerSpellsVo = summonerSpellsVo;
        mDateFormatter = new SimpleDateFormat("yyy/MM/dd");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(mContext).inflate(R.layout.row_)
        if (viewType == TYPE_ITEM) {
            //view item
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game_item, parent, false);
            return new ViewHolder(view, viewType);
        } else if (viewType == TYPE_HEADER) {
            //view header
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game_header, parent, false);

            return new ViewHolder(view, viewType);
        }

        //TODO return new ViewHolder(v, viewtype);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TeamVo team = mMatchDetailVo.teams.get(position == 0 ? 0 : 1);
        //needed to counter the added header cards
        if (position > ((mMatchDetailVo.participants.size() + 2) / 2)) {
            if (position == mMatchDetailVo.participants.size() + 1) {
                position = mMatchDetailVo.participants.size() - 1;
            } else {
                position = position - 2;
            }
        } else {
            position = position - 1;
        }

        if (holder.holderId == TYPE_HEADER) {
            //TODO banned champions

            holder.victoryDefeat.setText(team.winner ? "Victory" : "Defeat");
            holder.barons.setText("Barons: " + team.baronKills);
            holder.dragons.setText("Dragons: " + team.dragonKills);
            holder.towers.setText("Towers: " + team.towerKills);
            holder.riftHeraldKills.setText("Rift Herald: " + team.riftHeraldKills);
        } else {
            PlayerVo playerVo = mMatchDetailVo.participantIdentities.get(position).player;
            Participant participant = mMatchDetailVo.participants.get(position);
            ParticipantStatsVo stats = participant.stats;
            if (playerVo != null) {
                holder.playerName.setText(playerVo.summonerName);
            } else {
                holder.playerName.setText("Player " + participant.participantId);
                String championId = Integer.toString(participant.championId);
                Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/champion/" + mChampions.data.get(championId).image.full)
                       .placeholder(R.drawable.ic_launcher)
                       .into(holder.championIcon);


                Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/spell/" + mSummonerSpellsVo.data.get(Integer.toString(participant.spell1Id)).image.full)
                       .into(holder.summonerSpellOne);

                Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/spell/" + mSummonerSpellsVo.data.get(Integer.toString(participant.spell2Id)).image.full)
                       .into(holder.summonerSpellTwo);

                holder.killDeathAssist.setText(String.format(mContext.getString(R.string.kills_death_assist), stats.kills, stats.deaths, stats.assists));
                holder.creepScore.setText(Integer.toString(stats.minionsKilled));
                holder.goldEarned.setText(Long.toString(stats.goldEarned) + "K");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMatchDetailVo.participants.size() + 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int holderId;

        //HEADER
        TextView victoryDefeat;
        TextView barons;
        TextView dragons;
        TextView towers;
        TextView riftHeraldKills;

        //ITEM
        TextView playerName;
        ImageView championIcon;
        ImageView summonerSpellOne;
        ImageView summonerSpellTwo;
        TextView killDeathAssist;
        TextView creepScore;
        TextView goldEarned;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            itemView.setOnClickListener(this);

            if(viewType == TYPE_HEADER) {
                victoryDefeat = (TextView) itemView.findViewById(R.id.victoryDefeat);
                barons = (TextView) itemView.findViewById(R.id.barons);
                dragons = (TextView) itemView.findViewById(R.id.dragons);
                towers = (TextView) itemView.findViewById(R.id.towers);
                riftHeraldKills = (TextView) itemView.findViewById(R.id.riftHeraldKills);
                holderId = TYPE_HEADER;
            } else {
                playerName = (TextView) itemView.findViewById(R.id.playerName);
                championIcon = (ImageView) itemView.findViewById(R.id.championIcon);
                summonerSpellOne = (ImageView) itemView.findViewById(R.id.summoner_spell_one);
                summonerSpellTwo = (ImageView) itemView.findViewById(R.id.summoner_spell_two);
                killDeathAssist = (TextView) itemView.findViewById(R.id.kill_death_assist);
                creepScore = (TextView) itemView.findViewById(R.id.creep_score);
                goldEarned = (TextView) itemView.findViewById(R.id.gold_earned);
                holderId = TYPE_ITEM;
            }

        }

        @Override
        public void onClick(View v) {
            //TODO stuff, like show more data
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0 || position == 6;
    }
}
