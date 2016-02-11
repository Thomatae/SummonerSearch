package com.league2.app.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.league2.app.Module.ChampionsVo;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Service.StaticLeagueApi;
import com.league2.app.Vo.Champion;
import com.league2.app.Vo.GameVo;
import com.league2.app.Vo.RecentGamesVo;
import com.league2.app.Vo.SummonerSpellsVo;
import com.league2.app.adapter.RecentGamesAdapter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trethoma1 on 11/5/15.
 */

/*
    This fragment is to house recent games and ranked games

    Idea: Have a filter for Ranked and Normal
 */
public class GamesFragment extends Fragment {

    private static final String TITLE = "Games";

    private static final long NO_USER_ID = 0;

    private TextView mEmpty;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout mProgressLayout;

    private long mUserId = NO_USER_ID;

    @Inject LeagueApi mLeagueApi;

    @Inject StaticLeagueApi mStaticLeagueApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, null);

        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        mUserId = preferences.getLong(getString(R.string.user_id), NO_USER_ID);

        mEmpty = (TextView) view.findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ranked_recycler);
        mProgressLayout = (LinearLayout) view.findViewById(R.id.progress_layout);

        getGames();

        return view;
    }

    private void getGames() {
        mLeagueApi.getRecentGames("na", mUserId, getString(R.string.api_key), new Callback<RecentGamesVo>() {
            @Override
            public void success(RecentGamesVo recentGamesVo, Response response) {
                //After getting games, will need to grab champion info
                //possibly also spell info for icons
                getChampions(recentGamesVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }

    private void getChampions(final RecentGamesVo recentGamesVo) {
        mStaticLeagueApi.getChampions("na", getString(R.string.api_key), true, "all", new Callback<ChampionsVo>() {
            @Override
            public void success(ChampionsVo championsVo, Response response) {
                getSummonerSpells(recentGamesVo, championsVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

    }

    private void getSummonerSpells(final RecentGamesVo recentGamesVo, final ChampionsVo championsVo) {
        mStaticLeagueApi.getSummonerSpells("na", true, "all", getString(R.string.api_key), new Callback<SummonerSpellsVo>() {
            @Override
            public void success(SummonerSpellsVo summonerSpellsVo, Response response) {
                initializeAdapter(recentGamesVo, championsVo, summonerSpellsVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }

    private void initializeAdapter(RecentGamesVo recentGamesVo, ChampionsVo championsVo, SummonerSpellsVo summonerSpellsVo) {
        Log.d("Loop:", "intialize adapter");
        mAdapter = new RecentGamesAdapter(getActivity(), recentGamesVo.getGames(), championsVo, summonerSpellsVo);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressLayout.setVisibility(View.GONE);
    }

    public static String getTitle() {
        return TITLE;
    }
}
