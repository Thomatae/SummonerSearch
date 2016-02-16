package com.league2.app.adapter;

import org.w3c.dom.Text;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.league2.app.Module.ChampionsVo;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Service.StaticLeagueApi;
import com.league2.app.Vo.Champion;
import com.league2.app.Vo.GameStatsVo;
import com.league2.app.Vo.GameVo;
import com.league2.app.Vo.SummonerSpellsVo;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by trethoma1 on 11/5/15.
 */
public class RecentGamesAdapter extends RecyclerView.Adapter<RecentGamesAdapter.ViewHolder> {

    private Context mContext;
    private List<GameVo> mGameVos;
    private SimpleDateFormat mDateFormatter;
    private ChampionsVo mChampions;
    private SummonerSpellsVo mSummonerSpellsVo;
    private GameClickedListener mListener;

    public RecentGamesAdapter(Context context, GameClickedListener listener,
                              List<GameVo> gameVos,
                              ChampionsVo champions,
                              SummonerSpellsVo summonerSpellsVo) {
        mContext = context;
        mGameVos = gameVos;
        mChampions = champions;
        mSummonerSpellsVo = summonerSpellsVo;
        mListener = listener;
        mDateFormatter = new SimpleDateFormat("yyyy/MM/dd");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_recent_games, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //set data here
        GameVo gameVo = mGameVos.get(position);
        GameStatsVo stats = gameVo.getStats();

        holder.subType.setText(gameVo.subType);
        holder.gameMode.setText(gameVo.gameMode);

        //TODO set ImageViews
        String championId = Integer.toString(gameVo.championId);
        Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/champion/" + mChampions.data.get(championId).image.full)
               .placeholder(R.drawable.ic_launcher)
               .into(holder.championIcon);


        Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/spell/" + mSummonerSpellsVo.data.get(Integer.toString(gameVo.spell1)).image.full)
               .into(holder.summonerSpellOne);

        Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.3.1/img/spell/" + mSummonerSpellsVo.data.get(Integer.toString(gameVo.spell2)).image.full)
               .into(holder.summonerSpellTwo);

        holder.gameDate.setText(mDateFormatter.format(new Date(gameVo.createDate)));
        holder.gameLength.setText("Game Length: " + Integer.toString(stats.timePlayed / 60));

        holder.killDeathAssist.setText(String.format(mContext.getString(R.string.kills_death_assist), stats.championsKilled, stats.numDeaths, stats.assists));
        holder.creepScore.setText(Integer.toString(stats.minionsKilled));
        holder.goldEarned.setText(Integer.toString(stats.goldEarned) + "K");
    }

    @Override
    public int getItemCount() {
        return mGameVos.size();
    }

    public interface GameClickedListener {
        void onGameClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subType;
        TextView gameMode;
        ImageView championIcon;
        ImageView summonerSpellOne;
        ImageView summonerSpellTwo;
        TextView gameDate;
        TextView gameLength;
        TextView killDeathAssist;
        TextView creepScore;
        TextView goldEarned;

        GameClickedListener listener;

        public ViewHolder(View itemView, GameClickedListener listener) {
            super(itemView);

            this.listener = listener;

            subType = (TextView) itemView.findViewById(R.id.subType);
            gameMode = (TextView) itemView.findViewById(R.id.gameMode);
            championIcon = (ImageView) itemView.findViewById(R.id.championIcon);
            summonerSpellOne = (ImageView) itemView.findViewById(R.id.summoner_spell_one);
            summonerSpellTwo = (ImageView) itemView.findViewById(R.id.summoner_spell_two);
            gameDate = (TextView) itemView.findViewById(R.id.game_date);
            gameLength = (TextView) itemView.findViewById(R.id.game_length);
            killDeathAssist = (TextView) itemView.findViewById(R.id.kill_death_assist);
            creepScore = (TextView) itemView.findViewById(R.id.creep_score);
            goldEarned = (TextView) itemView.findViewById(R.id.gold_earned);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onGameClicked(getAdapterPosition());
            }
         }
    }

    private int getColor(int resourceId) {
        return mContext.getResources().getColor(resourceId);
    }
}
