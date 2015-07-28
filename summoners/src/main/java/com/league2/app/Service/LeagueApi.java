package com.league2.app.Service;

import com.league2.app.Vo.RecentGamesVo;
import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerInfoVo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface LeagueApi {
    @GET("/v1.4/summoner/by-name/{name}")
    void getSummonerStats(@Path("name") String name, @Query("api_key") String apiKey, Callback<SummonerInfoVo> callback);

    @GET("/v2.4/league/by-summoner/{id}/entry")
    void getSummonerLeagueStats(@Path("id") long id, @Query("api_key") String apiKey, Callback<SummonerIdLeagueVo> callback);

    @GET("/v1.3/game/by-summoner/{id}/recent")
    RecentGamesVo getRecentGames(@Path("id") long id, @Query("api_key") String apiKey);
}
