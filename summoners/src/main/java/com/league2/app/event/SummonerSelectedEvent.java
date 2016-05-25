package com.league2.app.event;

/**
 * Created by trethoma1 on 5/24/16.
 */
public class SummonerSelectedEvent {

    public final String summonerName;
    public final long summonerId;

    public SummonerSelectedEvent(String summonerName, long summonerId) {
        this.summonerId = summonerId;
        this.summonerName = summonerName;
    }
}
