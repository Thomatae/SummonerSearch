package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by trethoma1 on 2/15/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {

    public int championId;
    public String highestAchievedSeasonTier;
    //masteries
    public int participantId;
    //runes
    public int spell1Id;
    public int spell2Id;
    //stats Participant Stats
    public int teamId;
    //timeline
}
