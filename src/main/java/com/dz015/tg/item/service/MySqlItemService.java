package com.dz015.tg.item.service;

import com.dz015.tg.item.model.TGItem;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dz015.tg.item.model.TGItem.*;

public class MySqlItemService implements ItemService {

    @Inject
    private DataSource dataSource;

    @Override
    public TGItem getItem(long itemId) {
        StringBuffer path = new StringBuffer();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement statement = con.prepareCall("CALL getItemDetails(?)")
        ) {
            statement.setLong(1, itemId);
            ResultSet rs = statement.executeQuery();
            if (rs==null) {
                return null;
            }
            while (rs.next()) {
                path.append(rs.getString(1));
            }
            //
            // Sub categories are next
            //
            statement.getMoreResults();
            return new ItemBuilder(itemId).path(path.toString()).build();
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
