package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameVo {

    public long gameId;
    public int championId;
    public long createDate;
    public String gameMode;
    public String gameType;
    public int ipEarned;
    public int level;
    public int spell1;
    public int spell2;
    public GameStatsVo stats;
    public List<PlayerVo> fellowPlayers;
    public String subType;
    public int teamId;

    public GameStatsVo getStats() {
        return stats;
    }
}
