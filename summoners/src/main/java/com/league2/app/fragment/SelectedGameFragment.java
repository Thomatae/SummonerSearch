package com.league2.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.league2.app.R;

/**
 * Created by trethoma1 on 2/15/16.
 */
public class SelectedGameFragment extends Fragment {

    private static final String GAME_ID = "gameId";

    private long mGameId;

    private TextView mGameText;

    public static SelectedGameFragment newInstance(long gameId) {
        Bundle args = new Bundle();
        args.putLong(GAME_ID, gameId);
        SelectedGameFragment fragment = new SelectedGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //DaggerApplication.inject(this);

        mGameId = getArguments().getLong(GAME_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_game, null);

        mGameText = (TextView) view.findViewById(R.id.gameId);
        mGameText.setText("Game Id:" + mGameId);

        getGameData();

        return view;
    }

    private void getGameData() {

    }
}
