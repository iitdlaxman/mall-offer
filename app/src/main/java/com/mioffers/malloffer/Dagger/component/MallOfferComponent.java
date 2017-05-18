package com.mioffers.malloffer.dagger.component;

import com.mioffers.malloffer.dagger.module.AppModule;
import com.mioffers.malloffer.dagger.module.MallOfferModule;
import com.mioffers.malloffer.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by laxman.muttineni on 17/05/17.
 */
@Singleton
@Component(modules={AppModule.class, MallOfferModule.class})
public interface MallOfferComponent {

    void inject(MainActivity activity);

}
