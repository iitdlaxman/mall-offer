package com.mioffers.malloffer.dagger.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.mioffers.malloffer.R;
import com.mioffers.malloffer.core.connectivity.FireBaseHandler;
import com.mioffers.malloffer.core.connectivity.LocationHandler;
import com.mioffers.malloffer.core.connectivity.NetHandler;
import com.mioffers.malloffer.navDrawer.DrawerHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laxman.muttineni on 17/05/17.
 */
@Module
public class MallOfferModule {

    String mBaseUrl;

    public MallOfferModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    LocationHandler providesGPS(Application application) {
        return new LocationHandler(application);
    }

    @Provides
    @Singleton
    FireBaseHandler providesFirebase() {
        return new FireBaseHandler();
    }

    @Provides
    @Singleton
    ConnectivityManager providesConnectivityManager(Application application) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    @Provides
    @Singleton
    NetHandler providesNET(ConnectivityManager connectivityManager,
                           Application application) {
        return new NetHandler(connectivityManager, application);
    }

    @Provides
    @Singleton
    DrawerHandler providesNavDrawer(Activity activity) {
        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        return new DrawerHandler(activity, drawer, toolbar, navigationView);
    }
}
