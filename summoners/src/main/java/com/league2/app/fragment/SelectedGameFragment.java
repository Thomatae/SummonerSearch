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
import com.league2.app.Vo.Participant;
import com.league2.app.Vo.PlayerVo;
import com.league2.app.Vo.SummonerIDVo;
import com.league2.app.Vo.SummonerSpellsVo;
import com.league2.app.Vo.SummonerVo;
import com.league2.app.adapter.SelectedGameAdapter;
import com.league2.app.event.SummonerSelectedEvent;
import com.league2.app.util.CoreConstants;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class SelectedGameFragment extends Fragment implements SelectedGameAdapter.SummonerClickedListener {

    private static final String GAME_ID = "gameId";
    private static final String SUMMONER_IDS = "summonderIds";
    private static final String PLAYER_INFO = "playerInfo";

    private long mGameId;
    private String mSummonerIds;
    private List<SummonerVo> mSummonerInfo;
    private MatchDetailVo mMatchDetailVo;
    private ChampionsVo mChampionsVo;
    private SummonerSpellsVo mSummonerSpellsVo;
    private List<PlayerVo> mPlayInfo;

    private RecyclerView mRecyclerView;
    private LinearLayout mProgressLayout;
    private RelativeLayout mGameDetailsLayout;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Inject
    LeagueApi mLeagueApi;

    @Inject
    StaticLeagueApi mStaticLeagueApi;

    @Inject
    Bus mBus;

    public static SelectedGameFragment newInstance(long gameId, String summonderIds, ArrayList<PlayerVo> playerInfo) {
        Bundle args = new Bundle();
        args.putLong(GAME_ID, gameId);
        args.putString(SUMMONER_IDS, summonderIds);
        args.putParcelableArrayList(PLAYER_INFO, playerInfo);
        SelectedGameFragment fragment = new SelectedGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication.inject(this);
        Bundle arguments = getArguments();
        mGameId = arguments.getLong(GAME_ID);
        mSummonerIds = arguments.getString(SUMMONER_IDS);
        mPlayInfo = arguments.getParcelableArrayList(PLAYER_INFO);
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
                Log.d("MartchURL:", " " + response.getUrl());
                mMatchDetailVo = matchDetailVo;
                getSummonerNames();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Game Fail:", retrofitError.getBody().toString());
            }
        });
    }

    private void getSummonerNames() {
        mLeagueApi.getSummonersByID(CoreConstants.REGION_NA, mSummonerIds, getString(R.string.api_key), new Callback<SummonerIDVo>() {
            @Override
            public void success(SummonerIDVo summonerIDVo, Response response) {
                Log.d("SummhURL:", " " + response.getUrl());
                mSummonerInfo = new ArrayList<SummonerVo>(summonerIDVo.ids.values());
                getChampions();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
    }

    private void createRelationBetweenInfo() {
        //Since summoner names can only be acquired via getSummoner

        for (Participant participant : mMatchDetailVo.participants) {
            for (PlayerVo playerInfo : mPlayInfo) {
                if (playerInfo.championId == participant.championId) {
                    participant.summonerId = playerInfo.summonerId;
                    for (SummonerVo summonerInfo : mSummonerInfo) {
                        if (participant.summonerId == summonerInfo.id) {
                            participant.summonerName = summonerInfo.name;
                        }
                    }
                }

            }
        }
    }

    private void getChampions() {
        mStaticLeagueApi.getChampions(CoreConstants.REGION_NA, getString(R.string.api_key), true, CoreConstants.CHAMP_DATA_ALL, new Callback<ChampionsVo>() {
            @Override
            public void success(ChampionsVo championsVo, Response response) {
                mChampionsVo = championsVo;
                getSummonerSpells();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });

    }

    private void getSummonerSpells() {
        mStaticLeagueApi.getSummonerSpells(CoreConstants.REGION_NA,
                                           true,
                                           CoreConstants.CHAMP_DATA_ALL,
                                           getString(R.string.api_key),
                                           new Callback<SummonerSpellsVo>() {
                                               @Override
                                               public void success(SummonerSpellsVo summonerSpellsVo, Response response) {
                                                   mSummonerSpellsVo = summonerSpellsVo;
                                                   initializeAdapter();
                                               }

                                               @Override
                                               public void failure(RetrofitError retrofitError) {

                                               }
                                           });
    }

    @Override
    public void onSummonerClicked(int position) {
        //post bus event that summonerevent clicked
        //send summoner name/id from mMatchVo.particapant.get(position)
        Participant participant = mMatchDetailVo.participants.get(position);
        mBus.post(new SummonerSelectedEvent(participant.summonerName, participant.summonerId));
    }

    private void initializeAdapter() {
        createRelationBetweenInfo();
        mAdapter = new SelectedGameAdapter(getActivity(), mMatchDetailVo, mChampionsVo, mSummonerSpellsVo, this);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressLayout.setVisibility(View.GONE);
        mGameDetailsLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBus != null) {
            mBus.register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBus != null) {
            mBus.unregister(this);
        }
    }
}
