package com.dz015.tg.config;

import com.dz015.tg.service.data.DummyTGDataService;
import com.dz015.tg.service.data.TGDataService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class TGBinder extends AbstractBinder {

    @Override
    protected void configure() {

        bind(DummyTGDataService.class).to(TGDataService.class).in(Singleton.class);

    }

}
