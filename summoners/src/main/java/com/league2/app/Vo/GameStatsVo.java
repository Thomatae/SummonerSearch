package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStatsVo {

    public int assists;
    public int championsKilled;
    public int goldEarned;
    public int minionsKilled;
    public int numDeaths;
    public int timePlayed;
    public boolean win;
}
