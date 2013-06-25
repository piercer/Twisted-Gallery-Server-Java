package com.dz015.tg.service.data;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlTGDataService implements TGDataService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGCollection getCollection(long collectionId) {

        try (Connection con = dataSource.getConnection()) {

        }
        catch (SQLException e) {
            //JDBCTutorialUtilities.printSQLException(e);
        }
        return new TGCollection(collectionId);
    }

    @Override
    public TGCategory getCategory(long categoryId) {
        return null;
    }

}
