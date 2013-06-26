package com.dz015.tg.application;

import org.glassfish.jersey.server.ResourceConfig;

public class TwistedGalleryServer extends ResourceConfig {

    public TwistedGalleryServer() {
        register(new TGBinder());
        packages("com.dz015.tg.category.resource");
        packages("com.dz015.tg.collection.resource");
    }
}
