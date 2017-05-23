package com.mioffers.malloffer.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import com.mioffers.malloffer.core.connectivity.FireBaseHandler;
import com.mioffers.malloffer.core.connectivity.LocationHandler;
import com.mioffers.malloffer.core.connectivity.NetHandler;

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
}
