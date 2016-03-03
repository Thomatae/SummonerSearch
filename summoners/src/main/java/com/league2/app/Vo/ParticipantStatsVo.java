package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by trethoma1 on 2/23/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantStatsVo {

    public long kills;
    public long assists;
    public long champLevel;
    public long deaths;
    public long goldEarned;
    public int minionsKilled;
    public long item0;
    public long item1;
    public long item2;
    public long item3;
    public long item4;
    public long item5;
    public long item6;
    //other stats for more details button
}
