package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by trethoma1 on 9/29/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterLeagueVo {

    public List<SummonerLeagueStatsVo> id;

    @JsonAnySetter
    public void setAnyValue(String propertyName, List<SummonerLeagueStatsVo> id) {
        this.id = id;
    }
}
