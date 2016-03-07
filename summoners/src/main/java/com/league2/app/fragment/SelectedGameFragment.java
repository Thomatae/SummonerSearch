package com.league2.app.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
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
import android.widget.RelativeLayout;
import com.league2.app.Module.ChampionsVo;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Service.StaticLeagueApi;
import com.league2.app.Vo.MatchDetailVo;
import com.league2.app.Vo.SummonerSpellsVo;
import com.league2.app.adapter.SelectedGameAdapter;
import com.league2.app.util.CoreConstants;

import javax.inject.Inject;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class SelectedGameFragment extends Fragment {

    private static final String GAME_ID = "gameId";

    private long mGameId;
    private MatchDetailVo mMatchDetailVo;

    private RecyclerView mRecyclerView;
    private LinearLayout mProgressLayout;
    private RelativeLayout mGameDetailsLayout;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Inject
    LeagueApi mLeagueApi;

    @Inject
    StaticLeagueApi mStaticLeagueApi;

    public static SelectedGameFragment newInstance(long gameId) {
        Bundle args = new Bundle();
        args.putLong(GAME_ID, gameId);
        SelectedGameFragment fragment = new SelectedGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication.inject(this);

        mGameId = getArguments().getLong(GAME_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_game, null);

        mProgressLayout = (LinearLayout) view.findViewById(R.id.progress_layout);
        mGameDetailsLayout = (RelativeLayout) view.findViewById(R.id.game_details);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.summonerList);

        getGameData();

        return view;
    }

    private void getGameData() {
        mLeagueApi.getGame(CoreConstants.REGION_NA, getString(R.string.api_key), mGameId, new Callback<MatchDetailVo>() {
            @Override
            public void success(MatchDetailVo matchDetailVo, Response response) {
                Log.d("MatchId:", " " + matchDetailVo.matchId);
                mMatchDetailVo = matchDetailVo;
                getChampions();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Game Fail:", retrofitError.getBody().toString());
            }
        });
    }

    private void getChampions() {
        mStaticLeagueApi.getChampions(CoreConstants.REGION_NA, getString(R.string.api_key), true, CoreConstants.CHAMP_DATA_ALL, new Callback<ChampionsVo>() {
            @Override
            public void success(ChampionsVo championsVo, Response response) {
                getSummonerSpells(championsVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

    }

    private void getSummonerSpells(final ChampionsVo championsVo) {
        mStaticLeagueApi.getSummonerSpells(CoreConstants.REGION_NA, true, CoreConstants.CHAMP_DATA_ALL, getString(R.string.api_key), new Callback<SummonerSpellsVo>() {
            @Override
            public void success(SummonerSpellsVo summonerSpellsVo, Response response) {
                initializeAdapter(championsVo, summonerSpellsVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }

    private void initializeAdapter(final ChampionsVo championsVo, final SummonerSpellsVo summonerSpellsVo) {
        mAdapter = new SelectedGameAdapter(getActivity(), mMatchDetailVo, championsVo, summonerSpellsVo);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressLayout.setVisibility(View.GONE);
        mGameDetailsLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
