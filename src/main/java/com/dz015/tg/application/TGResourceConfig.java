package com.dz015.tg.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class TGResourceConfig extends ResourceConfig {

    public TGResourceConfig() {

        super(
                JacksonFeature.class
        );

        register(new TGBinder());
        packages("com.dz015.tg.category.resource");
        packages("com.dz015.tg.collection.resource");
    }
}
