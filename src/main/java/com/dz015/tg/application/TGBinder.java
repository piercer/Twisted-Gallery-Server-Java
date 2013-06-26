package com.dz015.tg.application;

import com.dz015.tg.category.service.CategoryService;
import com.dz015.tg.category.service.MySqlCategoryService;
import com.dz015.tg.collection.service.CollectionService;
import com.dz015.tg.collection.service.MySqlCollectionService;
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
        bind(MySqlCategoryService.class).to(CategoryService.class).in(Singleton.class);
        bind(MySqlCollectionService.class).to(CollectionService.class).in(Singleton.class);

    }

}
