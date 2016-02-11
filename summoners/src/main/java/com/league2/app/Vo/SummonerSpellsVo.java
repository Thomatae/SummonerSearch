package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by trethoma1 on 2/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerSpellsVo {
    public Map<String, SummonerSpell> data;
}
