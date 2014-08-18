package com.league2.app.Service;

import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerInfoVo;
import com.league2.app.Vo.SummonerLeagueStatsVo;

import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.Path;
import retrofit.http.Query;

public interface LeagueApi {
    @GET("/v1.4/summoner/by-name/{name}")
    SummonerInfoVo getSummonerStats(@Path("name") String name, @Query("api_key") String apiKey);

    @GET("/v2.4/league/by-summoner/{id}/entry")
    SummonerIdLeagueVo getSummonerLeagueStats(@Path("id") long id, @Query("api_key") String apiKey);
}
