package com.dz015.tg.config;

import org.glassfish.jersey.server.ResourceConfig;

public class TGResourceConfig extends ResourceConfig {

    public TGResourceConfig() {
        register(new TGBinder());
        packages("com.dz015.tg.resource");
    }
}
