package com.league2.app.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Vo.MasterLeagueVo;
import com.league2.app.adapter.RankedAdapter;

import javax.inject.Inject;

/**
 * Created by trethoma1 on 9/28/15.
 */
public class RankedFragment extends Fragment {

    public static final String TITLE = "Ranked";
    private static final long NO_USER_ID = -1;

    private TextView mEmpty;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout mProgressLayout;

    private long mUserId = NO_USER_ID;

    @Inject LeagueApi mLeagueApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked, null);

        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        mUserId = preferences.getLong(getString(R.string.user_id), NO_USER_ID);

        mEmpty = (TextView) view.findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ranked_recycler);
        mProgressLayout = (LinearLayout) view.findViewById(R.id.progress_layout);

        getLeagueInformation();

        return view;
    }

    private void getLeagueInformation() {
        mLeagueApi.getSummonerLeagueStats(mUserId, getString(R.string.api_key), new Callback<MasterLeagueVo>() {
            @Override
            public void success(MasterLeagueVo masterLeagueVo, Response response) {
                initializeAdapter(masterLeagueVo);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getActivity(), "Sorry there was an issue with your results", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeAdapter(MasterLeagueVo masterLeagueVo) {
        mAdapter = new RankedAdapter(getActivity(), masterLeagueVo.id);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressLayout.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static String getTitle() {
        return TITLE;
    }
}
