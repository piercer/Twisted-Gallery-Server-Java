package com.dz015.tg.service.data;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlTGDataService implements TGDataService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGCollection getCollection(long collectionId) {

        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("call getCollectionItems(?)")
        ) {
            statement.setLong(1, collectionId);
            ResultSet rs = statement.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            //JDBCTutorialUtilities.printSQLException(e);
        }
        return new TGCollection(collectionId);
    }

    @Override
    public TGCategory getCategory(long categoryId) {
        TGCategory category = new TGCategory(categoryId);
        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("call getCategoryDetails(?)")
        ) {
            statement.setLong(1, categoryId);
            statement.execute();
            //
            // Category parents are in the first rewsult set
            //
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                long parentID = rs.getLong(1);
                String parentName = rs.getString(2);
                category.addParent(parentID, parentName);
            }
            //
            // Child categories are next
            //
            statement.getMoreResults();
            rs = statement.getResultSet();
            //
            // Then child collections
            //
            statement.getMoreResults();
            rs = statement.getResultSet();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
            //JDBCTutorialUtilities.printSQLException(e);
        }
        return category;
    }

}
