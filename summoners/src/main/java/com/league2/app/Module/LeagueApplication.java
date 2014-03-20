package com.league2.app.Module;

import android.app.Application;

import com.league2.app.Service.LeagueApi;

import org.codehaus.jackson.map.ObjectMapper;

import retrofit.RestAdapter;

public class LeagueApplication extends Application {
    private static String LEAGUE_API = "https://prod.api.pvp.net/api/lol/na";

    private LeagueApi mLeagueApi;

    @Override public void onCreate() {
        super.onCreate();
        ObjectMapper mapper = new ObjectMapper();
        RestAdapter restAdapter = new RestAdapter.Builder().setServer(LEAGUE_API).setConverter(new JacksonConverter(mapper)).build();
        mLeagueApi = restAdapter.create(LeagueApi.class);
    }

    public LeagueApi getLeagueApi() {
        return mLeagueApi;
    }
}
