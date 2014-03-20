package com.league2.app.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Vo.SummonerInfoVo;

import javax.inject.Inject;

public class Homepage extends Fragment{

    @Inject LeagueApi mLeagueApi;

    private String API_KEY = "ddb565ce-33ad-435f-ac6c-82882a99c6f6";
    private SummonerInfoVo mSummonerInfoVo;
    private String mQuery;

    public interface CheckApiListener{
        void checkApiListener(final String name);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((DaggerApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        final View view = layoutInflater.inflate(R.layout.homepage_layout, null);
        final EditText apiEntry = (EditText) view.findViewById(R.id.summoner_name);
        final ProgressBar apiProgress = (ProgressBar) view.findViewById(R.id.check_api_progress);
        final Button checkApi = (Button) view.findViewById(R.id.api_enter);

        apiProgress.setVisibility(View.INVISIBLE);

        checkApi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkApi.setVisibility(View.VISIBLE);
                try {
                    mQuery = apiEntry.getText().toString();
                } catch (NullPointerException e) {

                }

                if (mQuery.equals("")) {
                    //put conditions where they didnt enter a string
                } else {
                    new RetrieveSummonerId().execute();

                }


            }
        });

        return view;

    }

    private void checkSummonerName(String name) {
        mSummonerInfoVo = mLeagueApi.getSummonerStats(mQuery, API_KEY);
        Log.d("summon", mSummonerInfoVo + "");
        ((CheckApiListener) getActivity()).checkApiListener(name);
    }

    private class RetrieveSummonerId extends AsyncTask<Void, Void, SummonerInfoVo> {

        protected SummonerInfoVo doInBackground(Void... here) {
                return mLeagueApi.getSummonerStats(mQuery, API_KEY);
        }

        protected void onPostExecute(SummonerInfoVo infoVo) {
            Log.d("checkMe", infoVo.getResults().getSummonerId() + "");

        }
    }


}
