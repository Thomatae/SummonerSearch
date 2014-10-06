package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameVo {

    public long createDate;
    public String gameMode;
    public String gameType;
    public int ipEarned;
    public int level;
    public int spell1;
    public int spell2;
    public GameStatsVo stats;
    public String subType;
    public int teamId;

    public GameStatsVo getStats() {
        return stats;
    }
}
