package com.dz015.tg.collection.service;

import com.dz015.tg.category.model.TGCategory;
import com.dz015.tg.collection.model.TGCollection;
import com.dz015.tg.item.model.TGItem;
import com.dz015.tg.item.model.TGMetaData;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySqlCollectionService implements CollectionService {

    @Inject
    private DataSource dataSource;

    private final Pattern metamatch = Pattern.compile("\\{##(.*?)##(.*?)##\\}");
    private final Pattern tagmatch = Pattern.compile("\\{##(.*?)##\\}");

    @Override
    public TGCollection getCollection(long collectionId) {

        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getCollectionItems(?)")
        ) {
            long id;
            String name;
            String description;
            String categoryName = "";

            statement.setLong(1, collectionId);
            statement.execute();

            ResultSet rs = statement.getResultSet();
            if (rs == null) {
                return null;
            }
            rs.next();
            String collectionName = rs.getString(1);
            int numPics = rs.getInt(2);

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
                Set<TGMetaData> metaData = new HashSet<>();
                String rawMetadata = rs.getString(6);
                if (rawMetadata != null) {
                    Matcher metaDataMatcher = metamatch.matcher(rawMetadata);
                    while (metaDataMatcher.find()) {
                        TGMetaData metadatum = new TGMetaData.MetaDataBuilder(metaDataMatcher.group(0), metaDataMatcher.group(1)).build();
                        metaData.add(metadatum);
                    }
                }
                TGItem item = new TGItem.ItemBuilder(rs.getInt(1))
                        .ofType(rs.getString(3))
                        .isPublished(rs.getBoolean(2))
                        .fromServer(rs.getString(4))
                        .withMetaData(metaData).build();
                items.add(item);
            }
            //item = {'id': row[0], 'isPublished': row[1], 'type': row[2], 'server': row[3]}

            //if row[4]:
            //item['date'] = str(row[4])

            //tags = []
            //if row[6]:
            //for name in tagmatch.findall(row[6]):
            //tags.append(name)

            //item['metadata'] = withMetaData
            //item['tags'] = tags
            //items.append(item)
            int nParents = parents.size();
            TGCategory thisCategory = parents.get(nParents - 1);

            return new TGCollection.CollectionBuilder(thisCategory.getId(), collectionName).nItems(numPics).items(items).build();

        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
