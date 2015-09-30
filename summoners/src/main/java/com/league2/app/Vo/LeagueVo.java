package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueVo {

    public String division;
    public boolean isHotStreak;
    public boolean isFreshBlood;
    public boolean isInactive;
    public boolean isVeteran;
    public MiniSeriesVo miniSeries;
    public int leaguePoints;
    public int wins;
    public int losses;
    public String playerOrTeamName;

    public MiniSeriesVo getSeries() {
        return miniSeries;
    }
}
