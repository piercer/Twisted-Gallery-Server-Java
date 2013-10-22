package com.dz015.tg.category.service;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.dz015.tg.category.model.TGCategory.CategoryBuilder;

public class MySqlCategoryService implements CategoryService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGCategory getCategory(long categoryId) {
        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getCategoryDetails(?)")
        ) {
            long id;
            String name;
            String description;
            statement.setLong(1, categoryId);
            statement.execute();
            //
            // TGCategory parents are in the first result set
            //
            ArrayList<TGCategory> parents = new ArrayList<>();
            ResultSet rs = statement.getResultSet();
            if (rs==null) {
                return null;
            }
            while (rs.next()) {
                id = rs.getLong(1);
                name = rs.getString(2);
                description = rs.getString(3);
                parents.add(new CategoryBuilder(id, name).description(description).build());
            }
            //
            // Sub categories are next
            //
            statement.getMoreResults();
            ArrayList<TGCategory> subCategories = new ArrayList<>();
            rs = statement.getResultSet();
            while (rs.next()) {
                id = rs.getLong(1);
                name = rs.getString(2);
                description = rs.getString(3);
                int nCollections = rs.getInt(4);
                int nItems = rs.getInt(5);
                int previewId = rs.getInt(7);
                subCategories.add(new CategoryBuilder(id, name)
                        .description(description)
                        .nCollections(nCollections)
                        .nItems(nItems)
                        .previewId(previewId)
                        .build());
            }
            //
            // Then child collections
            //
            statement.getMoreResults();
            rs = statement.getResultSet();
            ArrayList<TGCollection> collections = new ArrayList<>();
            while (rs.next()) {
                id = rs.getLong(1);
                name = rs.getString(2);
                int nItems = rs.getInt(3);
                int previewId = rs.getInt(5);
                collections.add(new TGCollection.CollectionBuilder(id, name)
                        .nItems(nItems)
                        .previewId(previewId)
                        .build());
            }
            //
            // Return the enclosing category
            //
            int nParents = parents.size();
            TGCategory thisCategory = parents.get(nParents - 1);
            return new CategoryBuilder(thisCategory.getId(), thisCategory.getName())
                    .parents(parents)
                    .subCategories(subCategories)
                    .collections(collections)
                    .build();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
