package com.league2.app.event;

import com.league2.app.Vo.PlayerVo;

import java.util.List;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class SelectedGameEvent {

    public final long mGameId;
    public final String mFellowPlayerIds;
    public final List<PlayerVo> mPlayerInfo;

    public SelectedGameEvent(long gameId, String fellowPlayerIds, List<PlayerVo> playerInfo) {
        mGameId = gameId;
        mFellowPlayerIds = fellowPlayerIds;
        mPlayerInfo = playerInfo;
    }
}
