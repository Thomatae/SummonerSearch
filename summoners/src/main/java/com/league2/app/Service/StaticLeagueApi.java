package com.league2.app.Service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import com.league2.app.Module.ChampionsVo;
import com.league2.app.Vo.Champion;

/**
 * Created by trethoma1 on 12/4/15.
 */
public interface StaticLeagueApi {

    //Grabs the key set for Champions needs a different rest adapter and to be in a different interface
    @GET("/{region}/v1.2/champion")
    void getChampions(@Path("region") String region, @Query("api_key") String apiKey, @Query("dataById") boolean dataById, @Query("champData") String champData, Callback<ChampionsVo> callback);

    @GET("/{region}/v1.2/champion/{id}")
    void getChampion(@Path("region") String region, @Path("id") int id, @Query("champData") String champData, @Query("api_key") String apiKey, Callback<Champion> callback);

}
