package com.dz015.tg.config;

import com.dz015.tg.service.data.MySqlTGDataService;
import com.dz015.tg.service.data.TGDataService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TGBinder extends AbstractBinder {

    @Override
    protected void configure() {

        //
        // Inject the datasource
        //
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/twistedgallery");
            bind(ds).to(DataSource.class);
        }
        catch (NamingException e) {

        }
        bind(MySqlTGDataService.class).to(TGDataService.class).in(Singleton.class);

    }

}
