package com.league2.app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.league2.app.R;

public class GeneralInfoFragment extends Fragment {
    private static final String SUMMONER_ID = "summonerId";
    private long mSummonerId;

    private TextView mIdView;

    public static GeneralInfoFragment newInstance(long sectionNumber) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putLong(SUMMONER_ID, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public GeneralInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSummonerId = getArguments().getLong(SUMMONER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        mIdView = (TextView) view.findViewById(R.id.summonerId);
        mIdView.setText(Long.toString(mSummonerId));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
