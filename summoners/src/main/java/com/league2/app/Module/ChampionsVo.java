package com.league2.app.Module;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.league2.app.Vo.Champion;

import java.util.Map;

/**
 * Created by trethoma1 on 2/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionsVo {
    public Map<String, Champion> data;
}
