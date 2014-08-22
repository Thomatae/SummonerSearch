package com.league2.app.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.league2.app.Adapters.RecentGamesAdapter;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Vo.RecentGamesVo;

import javax.inject.Inject;

public class RecentGamesFragment extends ListFragment {

    public static final String SUMMONER_ID = "recentGames.summonerId";
    private long mSummonerId;

    private ListView mListView;
    private RecentGamesAdapter mRecentGamesAdapter;

    @Inject LeagueApi mLeagueApi;

    public static RecentGamesFragment newInstance(long summonerId) {
        RecentGamesFragment fragment = new RecentGamesFragment();
        Bundle args = new Bundle();
        args.putLong(SUMMONER_ID, summonerId);
        fragment.setArguments(args);
        return fragment;
    }

    public RecentGamesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((DaggerApplication) getActivity().getApplication()).inject(this);

        if (getArguments() != null) {
            mSummonerId = getArguments().getLong(SUMMONER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_games, container, false);

        mListView = (ListView) view.findViewById(android.R.id.list);

        new RetrieveRecentGames().execute();

        return view;

    }

    private void initializeListViewAdapter(RecentGamesVo recentGamesVo) {
        mRecentGamesAdapter = new RecentGamesAdapter(getActivity(), recentGamesVo.getGames());
        mListView.setAdapter(mRecentGamesAdapter);
    }

    private class RetrieveRecentGames extends AsyncTask<Void, Void, RecentGamesVo> {

        protected RecentGamesVo doInBackground(Void... here) {
            return mLeagueApi.getRecentGames(mSummonerId, getString(R.string.api_key));
        }

        protected void onPostExecute(RecentGamesVo recentGamesVo) {
            Log.d("recentGames", "here: " + recentGamesVo.games);
            initializeListViewAdapter(recentGamesVo);
            this.cancel(true);
        }

    }
}
