package com.dz015.tg.collection.service;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;
import com.dz015.tg.item.model.TGItem;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlCollectionService implements CollectionService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGCollection getCollection(long collectionId) {

        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getCollectionItems(?)")
        ) {
            long id;
            String name;
            String description;
            String categoryName="";

            statement.setLong(1, collectionId);
            statement.execute();

            ResultSet rs = statement.getResultSet();
            if (rs==null) {
                return null;
            }
            rs.next();
            String collectionName = rs.getString(1);
            int numPics=rs.getInt(2);

            ArrayList<TGCategory> parents = new ArrayList<>();

            statement.getMoreResults();
            rs = statement.getResultSet();

            while (rs.next()) {
                id = rs.getLong(1);
                name = rs.getString(2);
                description = rs.getString(3);
                parents.add(new TGCategory.CategoryBuilder(id, name).description(description).build());
            }


            statement.getMoreResults();
            rs = statement.getResultSet();
            ArrayList<TGItem> items = new ArrayList<>();
            while (rs.next()) {
                TGItem item = new TGItem.ItemBuilder(rs.getInt(1)).published(rs.getBoolean(2)).build();
                items.add(item);
            }
            //item = {'id': row[0], 'published': row[1], 'type': row[2], 'server': row[3]}

            //if row[4]:
            //item['date'] = str(row[4])

            //metaData = []
            //if row[5]:
            //for name, value in metamatch.findall(row[5]):
            //metaData.append({'name': name, 'value': value})

            //tags = []
            //if row[6]:
            //for name in tagmatch.findall(row[6]):
            //tags.append(name)

            //item['metadata'] = metaData
            //item['tags'] = tags
            //items.append(item)
            int nParents = parents.size();
            TGCategory thisCategory = parents.get(nParents - 1);

            return new TGCollection.CollectionBuilder(thisCategory.getId(),collectionName).nItems(numPics).items(items).build();

        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
