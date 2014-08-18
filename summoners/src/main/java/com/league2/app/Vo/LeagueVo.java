package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueVo {

    public String division;
    public boolean isHotStreak;
    public MiniSeriesVo miniSeries;
    public int leaguePoints;
    public int wins;
    public String playerOrTeamName;

    public MiniSeriesVo getSeries() {
        return miniSeries;
    }
}
