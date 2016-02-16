package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by trethoma1 on 2/15/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantIdentities {

    public int participantId;
    public Player player;
}
