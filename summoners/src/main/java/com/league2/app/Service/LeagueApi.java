package com.league2.app.Service;

import com.league2.app.Vo.SummonerInfoVo;

import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.Path;
import retrofit.http.Query;

public interface LeagueApi {
    @GET("/v1.3/summoner/by-name/{name}")
    SummonerInfoVo getSummonerStats(@Path("name") String name, @Query("api_key") String apiKey);
}
