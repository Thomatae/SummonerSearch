package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by trethoma1 on 2/10/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Champion {
    public int id;
    public String title;
    public String name;
    public ImageDTO image;
}
