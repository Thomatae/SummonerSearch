package com.league2.app.Fragments;

import org.w3c.dom.Text;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerLeagueStatsVo;

import javax.inject.Inject;

public class GeneralInfoFragment extends Fragment {
    private static final String SUMMONER_ID = "summonerId";
    private long mSummonerId;

    private TextView mCurrentRank;
    private TextView mNumberOfWins;
    private TextView mSeriesProgress;
    private TextView mSummonerName;
    private TextView mLeagueName;

    @Inject LeagueApi mLeagueApi;

    public static GeneralInfoFragment newInstance(long sectionNumber) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putLong(SUMMONER_ID, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public GeneralInfoFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        mCurrentRank = (TextView) view.findViewById(R.id.current_rank);
        mNumberOfWins = (TextView) view.findViewById(R.id.league_points);
        mSeriesProgress = (TextView) view.findViewById(R.id.progress);
        mSummonerName = (TextView) view.findViewById(R.id.summoner_name);
        mLeagueName = (TextView) view.findViewById(R.id.name_of_league);

        initialize();

        return view;
    }

    private void initialize() {
        new RetreiveLeagueInfo().execute();
    }

    private void setValues(SummonerLeagueStatsVo statsVo) {
        mCurrentRank.setText(statsVo.tier + " " + statsVo.getResutls().division);
        mNumberOfWins.setText(Integer.toString(statsVo.getResutls().leaguePoints));

        if (statsVo.getResutls().getSeries().progress != null) {
            mSeriesProgress.setText(statsVo.getResutls().getSeries().progress);
        }

        mSummonerName.setText(statsVo.getResutls().playerOrTeamName);
        mLeagueName.setText(statsVo.name);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class RetreiveLeagueInfo extends AsyncTask<Void, Void, SummonerIdLeagueVo> {

        protected SummonerIdLeagueVo doInBackground(Void... here) {

            return mLeagueApi.getSummonerLeagueStats(mSummonerId, getString(R.string.api_key));
        }

        protected void onPostExecute(SummonerIdLeagueVo infoVo) {
            setValues(infoVo.getResults());
            this.cancel(true);
        }
    }

}
