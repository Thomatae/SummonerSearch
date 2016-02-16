package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by trethoma1 on 2/15/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailVo {

    public long matchId;
    public String matchType;
    public List<ParticipantIdentities> participantIdentities;
    public List<Participant> participants;
    public String queueType;
    public String region;
    public String season;
    public List<TeamVo> teams;
}
