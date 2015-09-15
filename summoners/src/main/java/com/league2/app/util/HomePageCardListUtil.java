package com.league2.app.util;

import com.league2.app.Vo.BaseHomePageCard;
import com.league2.app.Vo.RankedHomePageCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trethoma1 on 9/14/15.
 */

/**
 * Houses the List of HomeCards
 * and Generated JSON*/
public class HomePageCardListUtil {

    private List<BaseHomePageCard> mHomeCards;
    private String mHomeJson;

    public HomePageCardListUtil(String json) {
        mHomeJson = json;
    }

    public List<BaseHomePageCard> getHomeCards() {
        if (mHomeJson == null) {
            mHomeCards = initializeHomeCards();
        } else {
            mHomeCards = JSONUtil.toCards(mHomeJson);
        }

        return mHomeCards;
    }

    private List<BaseHomePageCard> initializeHomeCards() {
        //TODO generate the first set of home cards
        mHomeCards = getInitialList();
        mHomeJson = JSONUtil.toJSON(mHomeCards);

        return mHomeCards;
    }

    private String getJsonForHomeCards() {
        return mHomeJson;
    }

    public List<BaseHomePageCard> getInitialList() {
        List<BaseHomePageCard> initialList = new ArrayList<>();

        //TODO add all cards for homepage here
        initialList.add(new RankedHomePageCard());

        return initialList;
    }

    public void addHomePageCard(BaseHomePageCard card) {
        mHomeCards.add(card);
        mHomeJson = JSONUtil.toJSON(mHomeCards);
    }
}
