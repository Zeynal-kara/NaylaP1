package com.example.naylap1.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.example.naylap1.R;

public class DialogHelper {

    public static AlertDialog.Builder alertBuilder(Context context) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context,
                R.style.ShowAlertDialogTheme));
    }
}