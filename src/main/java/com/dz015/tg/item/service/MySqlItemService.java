package com.dz015.tg.item.service;

import com.dz015.tg.item.model.TGItem;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.dz015.tg.item.model.TGItem.ItemBuilder;

public class MySqlItemService implements ItemService {

    @Inject
    private DataSource dataSource;

    @Inject
    private Properties properties;

    @Override
    public TGItem getItem(long itemId) {
        StringBuilder path = new StringBuilder(properties.getProperty("baseItemPath"));
        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getItemDetails(?)")
        ) {
            statement.setLong(1, itemId);
            ResultSet rs = statement.executeQuery();
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                path.append(rs.getString(2));
            }
            //
            // Sub categories are next
            //
            statement.getMoreResults();
            return new ItemBuilder(itemId).at(path.toString()).build();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
