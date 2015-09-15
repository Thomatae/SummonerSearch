package com.league2.app.Vo;

/**
 * Created by trethoma1 on 9/14/15.
 */
public class BaseHomePageCard {

    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String SUBTITLE = "subtitle";
    public static final String ID = "id";
    public static final String POSITION = "position";

    public String mType;
    public String mTitle;
    public String mSubtitle;
    public long mId;
    public long mPosition;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getPosition() {
        return mPosition;
    }

    public void setPosition(long position) {
        mPosition = position;
    }
}
