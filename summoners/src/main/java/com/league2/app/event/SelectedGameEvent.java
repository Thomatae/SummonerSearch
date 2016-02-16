package com.league2.app.event;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class SelectedGameEvent {

    public final long mGameId;

    public SelectedGameEvent(long gameId) {
        mGameId = gameId;
    }
}
