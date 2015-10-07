package com.league2.app.Service;

import com.league2.app.Vo.MasterLeagueVo;
import com.league2.app.Vo.RecentGamesVo;
import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerInfoVo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

public interface LeagueApi {
    @GET("/v1.4/summoner/by-name/{name}")
    void getSummonerStats(@Path("name") String name, @Query("api_key") String apiKey, Callback<SummonerInfoVo> callback);

    //TODO this now returns a list of SummonerIdLeagueVO
    @GET("/v2.5/league/by-summoner/{id}/entry")
    void getSummonerLeagueStats(@Path("id") long id, @Query("api_key") String apiKey, Callback<MasterLeagueVo> callback);

    @GET("/v1.3/game/by-summoner/{id}/recent")
    RecentGamesVo getRecentGames(@Path("id") long id, @Query("api_key") String apiKey);
}
