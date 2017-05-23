package com.mioffers.malloffer.core.connectivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.mioffers.malloffer.utils.Constants;

import javax.inject.Inject;

/**
 * Created by laxman.muttineni on 19/05/17.
 */
public class NetHandler {

    @Inject
    ConnectivityManager connectivityManager;
    @Inject
    Application application;

    public NetHandler(ConnectivityManager connectivityManager,
                      Application application) {
        this.connectivityManager = connectivityManager;
        this.application = application;
    }

    public void checkNet() {
        if (!isOnline(connectivityManager))
        {
            showNoConnectionDialog(application);
        }
    }

    private void showNoConnectionDialog(final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage(Constants.NET_SETTINGS_MSG);
        builder.setTitle(Constants.NET_SETTINGS_TITLE);
        builder.setPositiveButton(Constants.NET_SETTINGS_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });

        builder.setNegativeButton(Constants.NET_CANCEL_BUTTON, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });

        builder.show();
    }

    public boolean isOnline(ConnectivityManager connectivityManager)
    {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
