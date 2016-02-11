package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by trethoma1 on 2/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerSpell {
    public int id;
    public String description;
    public String name;
    public ImageDTO image;
}
