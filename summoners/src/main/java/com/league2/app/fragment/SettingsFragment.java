package com.league2.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.league2.app.Module.DaggerApplication;
import com.league2.app.R;
import com.league2.app.event.ProfileUpdatedEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by trethoma1 on 9/30/15.
 */

//TODO possibly just make this a normal fragment so it can handle differently
    //just use Clear User Name to reset Home
    //could later on use Clear All Data
public class SettingsFragment extends Fragment implements ConfirmationDialogFragment.ConfirmationDialgoFragmentListener {

    private LinearLayout mClearUserProfile;
    private ConfirmationDialogFragment mDialogFragment;

    @Inject Bus mBus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerApplication.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);

        mClearUserProfile = (LinearLayout) view.findViewById(R.id.clear_profile);
        mDialogFragment = new ConfirmationDialogFragment();
        mDialogFragment.setConfirmationDialgoFragmentListener(this);
        mClearUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogFragment.show(getActivity().getSupportFragmentManager(), "Clear Profile");
            }
        });

        return view;
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

    @Override
    public void onDialogPositiveClick() {
        mBus.post(new ProfileUpdatedEvent(true));
    }

    @Override
    public void onDialogNegativeClick() {

    }
}
