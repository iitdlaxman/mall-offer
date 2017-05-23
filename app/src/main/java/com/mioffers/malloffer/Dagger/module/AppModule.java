package com.mioffers.malloffer.dagger.module;

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

    public AppModule(Application application) {
        this.application = application;
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

}
