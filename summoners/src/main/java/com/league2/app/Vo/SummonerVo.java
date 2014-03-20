package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerVo {
    public long id;
    public String name;
    public int profileIconId;
    public long revisionDate;
    public long summonerLevel;

    public long getSummonerId() {
        return id;
    }
}
