package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueVo {

    public String division;
    public boolean isHotStreak;
    public int leaguePoints;
    public int wins;
}
