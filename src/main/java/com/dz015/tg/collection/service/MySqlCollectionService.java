package com.dz015.tg.collection.service;

import com.dz015.tg.collection.model.TGCollection;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlCollectionService implements CollectionService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGCollection getCollection(long collectionId) {

        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getCollectionItems(?)")
        ) {
            statement.setLong(1, collectionId);
            ResultSet rs = statement.executeQuery();
            if (rs==null) {
                return null;
            }
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
