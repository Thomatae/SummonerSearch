package com.league2.app.Service;

import com.league2.app.Vo.MasterLeagueVo;
import com.league2.app.Vo.MatchDetailVo;
import com.league2.app.Vo.RecentGamesVo;
import com.league2.app.Vo.SummonerIdLeagueVo;
import com.league2.app.Vo.SummonerInfoVo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

public interface LeagueApi {
    @GET("/{region}/v1.4/summoner/by-name/{name}")
    void getSummonerStats(@Path("region") String region,
                          @Path("name") String name,
                          @Query("api_key") String apiKey,
                          Callback<SummonerInfoVo> callback);

    //TODO this now returns a list of SummonerIdLeagueVO
    @GET("/{region}/v2.5/league/by-summoner/{id}/entry")
    void getSummonerLeagueStats(@Path("region") String region, @Path("id") long id, @Query("api_key") String apiKey, Callback<MasterLeagueVo> callback);

    @GET("/{region}/v1.3/game/by-summoner/{id}/recent")
    void getRecentGames(@Path("region") String region, @Path("id") long id, @Query("api_key") String apiKey, Callback<RecentGamesVo> callback);

    //TODO create the CALL BACK
    @GET("/{region}/v2.2/match/{matchId}")
    void getGame(@Path("region") String region, @Query("api_key") String apiKey, Callback<MatchDetailVo> callback);
}
