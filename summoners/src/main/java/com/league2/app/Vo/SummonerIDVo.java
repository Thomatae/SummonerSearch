package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trethoma1 on 3/15/16.
 */
public class SummonerIDVo {

    public Map<String, SummonerVo> ids = new HashMap<String, SummonerVo>();

    @JsonAnySetter
    public void setAnyValue(String propertyName, SummonerVo name) {
        ids.put(propertyName, name);
    }
}
