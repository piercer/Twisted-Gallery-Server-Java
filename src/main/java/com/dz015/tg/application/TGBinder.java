package com.dz015.tg.application;

import com.dz015.tg.category.service.CategoryService;
import com.dz015.tg.category.service.MySqlCategoryService;
import com.dz015.tg.collection.service.CollectionService;
import com.dz015.tg.collection.service.MySqlCollectionService;
import org.apache.commons.dbcp.BasicDataSource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TGBinder extends AbstractBinder {

    @Override
    protected void configure() {

        //
        // Inject the datasource
        //
        try {

            Properties prop = new Properties();

            prop.load(new FileInputStream("src/main/resources/tg.properties"));

            System.out.println("Properties loaded");

            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setDriverClassName(prop.getProperty("driverClassName"));
            dataSource.setUsername(prop.getProperty("username"));
            dataSource.setPassword(prop.getProperty("password"));
            dataSource.setUrl(prop.getProperty("url"));
            dataSource.setMaxActive(Integer.parseInt(prop.getProperty("maxActive")));
            dataSource.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));
            dataSource.setInitialSize(Integer.parseInt(prop.getProperty("initialSize")));
            dataSource.setValidationQuery(prop.getProperty("validationQuery"));

            bind(dataSource).to(DataSource.class);
        }
        catch (IOException e) {
            Logger.getLogger(TGBinder.class.getName()).log(Level.SEVERE, "Error initialising application datasource: " + e.getMessage());
        }
        //
        // Inject the endpoint services
        //
        bind(MySqlCategoryService.class).to(CategoryService.class).in(Singleton.class);
        bind(MySqlCollectionService.class).to(CollectionService.class).in(Singleton.class);

    }

}
