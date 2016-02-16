package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by trethoma1 on 2/15/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamVo {

    public List<BannedChampionVo> bans;
    public int baronKills;
    public int dragonKills;
    public int riftHeraldKills;
    public int teamId;
    public int towerKills;
    public boolean winner;
}
