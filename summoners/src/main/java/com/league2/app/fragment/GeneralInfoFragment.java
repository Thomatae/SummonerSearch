package com.league2.app.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.league2.app.adapter.LeaguesAdapter;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerLeagueStatsVo;

import javax.inject.Inject;
import java.util.List;

public class GeneralInfoFragment extends Fragment {
    private static final String SUMMONER_ID = "summonerId";
    private long mSummonerId;

    private ListView mListView;
    private LeaguesAdapter mLeagueAdapter;
    private TextView mSummonerName;

    @Inject LeagueApi mLeagueApi;

    public static GeneralInfoFragment newInstance(long summonerId) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putLong(SUMMONER_ID, summonerId);
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

        mListView = (ListView) view.findViewById(R.id.general_list);

        mSummonerName = (TextView) view.findViewById(R.id.summoner_name);

        getLeagueInformation();

        return view;
    }

    private void getLeagueInformation() {
        mLeagueApi.getSummonerLeagueStats(mSummonerId, getString(R.string.api_key), new Callback<SummonerIdLeagueVo>() {
            @Override
            public void success(SummonerIdLeagueVo summonerIdLeagueVo, Response response) {
                initializeListViewAdapter(summonerIdLeagueVo.getResults());
                mSummonerName.setText(summonerIdLeagueVo.getResults().get(0).getResutls().playerOrTeamName);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getActivity(), "Problems with retrofit", Toast.LENGTH_SHORT);
            }
        });
    }

    private void initializeListViewAdapter(List<SummonerLeagueStatsVo> leagues) {
        mLeagueAdapter = new LeaguesAdapter(getActivity(), leagues);
        mListView.setAdapter(mLeagueAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
