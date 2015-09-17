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

    public static BaseHomePageCard getCardFromType(String cardType) {
        BaseHomeCardType type = BaseHomeCardType.fromValue(cardType);
        return type.accept(new ViewTypeVisitor(), null);
    }

    public enum BaseHomeCardType {

        RANKED("ranked"){
            @Override
            <T, V> T accept(Visitor<T, V> visitor, V value) {
                return visitor.visitRanked(value);
            }
        };

        public String value;

        BaseHomeCardType(String value) {
            this.value = value;
        }

        static BaseHomeCardType fromValue(String value) {
            for (BaseHomeCardType type : values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }

            throw new IllegalArgumentException(String.format("ViewType does not have a return type for %s. Something is incredibly messed up.", value));
        }

        abstract <T,V> T accept(Visitor<T,V> visitor, V value);

        public static interface Visitor<T,V> {
            T visitRanked(V value);
        }
    }

    public static class ViewTypeVisitor implements BaseHomePageCard.BaseHomeCardType.Visitor<BaseHomePageCard, Void> {

        @Override
        public BaseHomePageCard visitRanked(Void value) {
            return new RankedHomePageCard();
        }
    }
}
