package com.example.naylap1.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.example.naylap1.R;

public class DialogHelper {

    static AlertDialog.Builder mAlertDialogBuilder;

    public static AlertDialog.Builder alertBuilder(Context context) {

        if (mAlertDialogBuilder == null)
            mAlertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    R.style.ShowAlertDialogTheme));

        return mAlertDialogBuilder;
    }
}