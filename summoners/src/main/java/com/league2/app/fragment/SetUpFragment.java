package com.league2.app.fragment;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.Service.LeagueApi;
import com.league2.app.Service.SummonerErrorHandler;
import com.league2.app.Vo.SummonerInfoVo;
import com.league2.app.event.ProfileUpdatedEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class SetUpFragment extends Fragment{

    public static final String TITLE = "Set Up";

    private static final String ARG_USER_NAME = "user_name";
    private static final long NO_USER_ID = 0;
    private static final int NO_ICON_ID = 0;

    @Inject LeagueApi mLeagueApi;

    @Inject Bus mBus;

    private SummonerErrorHandler mSummonerErrorHandler;
    private CardView mGetStartedCard;
    private EditText mSummonerName;
    private Button mUpdate;
    private ProgressBar mProgress;

    private String mUserName;
    private long mUserId = NO_USER_ID;
    private int mProfileIconId = NO_ICON_ID;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication.inject(this);

        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        mUserName = preferences.getString(getString(R.string.user_name), getString(R.string.default_user_name));
        mUserId = preferences.getLong(getString(R.string.user_id), NO_USER_ID);
        mProfileIconId = preferences.getInt(getString(R.string.user_profile_icon_id), NO_ICON_ID);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        View view = layoutInflater.inflate(R.layout.homepage_layout, null);
        mGetStartedCard = (CardView) view.findViewById(R.id.add_user_card);
        mSummonerName = (EditText) view.findViewById(R.id.edit_user_name);
        mUpdate = (Button) view.findViewById(R.id.update);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        mProgress.setVisibility(View.INVISIBLE);

        setUpdateName();


        if (mUserName.equals(getString(R.string.default_user_name)) && mUserId == NO_USER_ID) {
            //TODO add home user view here
            mGetStartedCard.setVisibility(View.VISIBLE);
        } else if (!mUserName.equals(getString(R.string.default_user_name)) && mUserId == NO_USER_ID) {
            //TODO add loading view and actually grab ID
            getSummonerId();
        }

        return view;
    }

    public static String getTitle() {
        return TITLE;
    }

    private void setUpdateName() {
        mSummonerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction() == KeyEvent.KEYCODE_ENTER && !v.getText().equals("")) {
                    updateUsername(v.getText().toString());
                }

                return false;
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add spinner to notify loading
                if (!mSummonerName.getText().toString().equals("")) {
                    updateUsername(mSummonerName.getText().toString());
                    getSummonerId();
                }
            }
        });
    }

    private void getSummonerId() {
        mProgress.setVisibility(View.VISIBLE);
        mLeagueApi.getSummonerStats("na",mUserName, getString(R.string.api_key), new Callback<SummonerInfoVo>() {
            @Override
            public void success(SummonerInfoVo summonerInfoVo, Response response) {
                if (summonerInfoVo.getResults().getSummonerId() != 0) {
                    updateUserId(summonerInfoVo.getResults().getSummonerId(), summonerInfoVo.getResults().getProfileIconId());
                    updateUsername(summonerInfoVo.getResults().name);
                    mBus.post(new ProfileUpdatedEvent(false));
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                mProgress.setVisibility(View.INVISIBLE);
                retrofitError.printStackTrace();
                Toast.makeText(getActivity(), "Sorry there was an issue with your summoner name", Toast.LENGTH_SHORT);
            }
        });
    }

    private void updateUserId(long userId, int profileIconId) {
        mUserId = userId;
        mProfileIconId = profileIconId;
        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(getString(R.string.user_id), userId);
        editor.putInt(getString(R.string.user_profile_icon_id), profileIconId);
        editor.apply();
    }

    private void updateUsername(String userName) {
        mUserName = userName;
        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.user_name), userName);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();

        mBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        mBus.unregister(this);
    }
}
