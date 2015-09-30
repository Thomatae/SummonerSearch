package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerLeagueStatsVo {

    public String queue;
    public String name;
    public List<LeagueVo> entries;
    public String tier;

    public List<LeagueVo> getResults() {
        return entries;
    }

}
