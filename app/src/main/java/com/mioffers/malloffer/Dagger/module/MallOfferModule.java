package com.mioffers.malloffer.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mioffers.malloffer.core.connectivity.GPSTracker;

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
    GPSTracker providesGPS() {
        return new GPSTracker();
    }
}
