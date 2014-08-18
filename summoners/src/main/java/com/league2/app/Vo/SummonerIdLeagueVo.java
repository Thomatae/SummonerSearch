package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonAnySetter;

import java.util.List;

public class SummonerIdLeagueVo {
    public List<SummonerLeagueStatsVo> id;

    public SummonerLeagueStatsVo getResults() {
        return id.get(0);
    }

    @JsonAnySetter
    public void setAnyValue(String propertyName, List<SummonerLeagueStatsVo> id) {
        this.id = id;
    }
}
