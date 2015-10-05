package com.league2.app.event;

/**
 * Created by trethoma1 on 9/15/15.
 */
public class ProfileUpdatedEvent {

    public boolean isClear;

    public ProfileUpdatedEvent(boolean isClear) {
        this.isClear = isClear;
    }
}
