package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonAnySetter;

import java.util.List;

public class SummonerIdLeagueVo {
    public List<SummonerLeagueStatsVo> id;

    public List<SummonerLeagueStatsVo> getResults() {
        return id;
    }

    @JsonAnySetter
    public void setAnyValue(String propertyName, List<SummonerLeagueStatsVo> id) {
        this.id = id;
    }
}
