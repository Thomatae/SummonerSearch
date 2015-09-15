package com.league2.app.util;

/**
 * Created by trethoma1 on 9/14/15.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.league2.app.Vo.BaseHomePageCard;

import java.util.ArrayList;
import java.util.List;

/**
* This class helps create JSON Objects to and from JSON*/
public class JSONUtil {

    private static final String JSON_LIST = "jsonList";

    public static String toJSON(List<BaseHomePageCard> cards) {
        try {
            JSONObject mainObject = new JSONObject();
            JSONArray cardList = new JSONArray();

            for (BaseHomePageCard card : cards) {
                JSONObject object = new JSONObject();
                object.put(BaseHomePageCard.TITLE, card.getTitle());
                object.put(BaseHomePageCard.ID, card.getId());
                object.put(BaseHomePageCard.POSITION, card.getPosition());
                object.put(BaseHomePageCard.SUBTITLE, card.getSubtitle());
                object.put(BaseHomePageCard.TYPE, card.getType());

                cardList.put(object);
            }

            mainObject.put(JSON_LIST, cardList);
            return mainObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BaseHomePageCard> toCards(String json) {
        //TODO generate cards based on type?
        List<BaseHomePageCard> homePageCards = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray objectList = object.getJSONArray(JSON_LIST);

            for (int i = 0; i < objectList.length(); i++) {
                JSONObject listItem = objectList.getJSONObject(i);
                //TODO do a switch through types
            }

            return homePageCards;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
