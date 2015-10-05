package com.league2.app.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import com.league2.app.R;

/**
 * Created by trethoma1 on 10/1/15.
 */
public class ConfirmationDialogFragment extends DialogFragment {

    private ConfirmationDialgoFragmentListener mConfirmationDialgoFragmentListener;

    public interface ConfirmationDialgoFragmentListener {
        void onDialogPositiveClick();
        void onDialogNegativeClick();
    }

    public void setConfirmationDialgoFragmentListener(ConfirmationDialgoFragmentListener listener) {
        mConfirmationDialgoFragmentListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.clear_user_desc).setTitle(R.string.clear_user_title)
               .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       mConfirmationDialgoFragmentListener.onDialogPositiveClick();
                       dismiss();
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       mConfirmationDialgoFragmentListener.onDialogNegativeClick();
                       dismiss();
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        //TODO this doesnt work
//        //post a bus event? or have main activity handle it
//        mConfirmationDialgoFragmentListener = (ConfirmationDialgoFragmentListener) activity;
//    }
}
