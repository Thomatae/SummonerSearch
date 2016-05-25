package com.league2.app.Vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class PlayerVo implements Parcelable {
    public String matchHistoryUri;
    public int profileIcon;
    public long summonerId;
    public String summonerName;

    //SummonerID specific
    public int championId;
    public int teamId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.matchHistoryUri);
        dest.writeInt(this.profileIcon);
        dest.writeLong(this.summonerId);
        dest.writeString(this.summonerName);
        dest.writeInt(this.championId);
        dest.writeInt(this.teamId);
    }

    public PlayerVo() {
    }

    protected PlayerVo(Parcel in) {
        this.matchHistoryUri = in.readString();
        this.profileIcon = in.readInt();
        this.summonerId = in.readLong();
        this.summonerName = in.readString();
        this.championId = in.readInt();
        this.teamId = in.readInt();
    }

    public static final Parcelable.Creator<PlayerVo> CREATOR = new Parcelable.Creator<PlayerVo>() {
        @Override
        public PlayerVo createFromParcel(Parcel source) {
            return new PlayerVo(source);
        }

        @Override
        public PlayerVo[] newArray(int size) {
            return new PlayerVo[size];
        }
    };
}
