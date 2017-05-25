package com.mioffers.malloffer.dagger.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.mioffers.malloffer.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laxman.muttineni on 01/05/17.
 */
@Module
public class AppModule {

    private final Application application;

    private final MainActivity activity;

    public AppModule(Application application,
                     MainActivity activity) {
        this.application = application;
        this.activity = activity;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public MainActivity providesActivity() {
        return this.activity;
    }
}
