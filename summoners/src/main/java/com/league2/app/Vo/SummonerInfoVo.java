package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonAnySetter;

public class SummonerInfoVo {

    public SummonerVo name;


    public SummonerVo getResults() {
        return name;
    }

    @JsonAnySetter
    public void setAnyValue(String propertyName, SummonerVo name) {
        this.name = name;
    }
}
