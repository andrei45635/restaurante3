package com.example.restaurante3.repo.db;

import com.example.restaurante3.domain.MenuItem;
import com.example.restaurante3.repo.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepoDB implements IRepository<Integer, MenuItem> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menuitems";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int menuItemID = resultSet.getInt("menuitemid");
                String category = resultSet.getString("category");
                String item = resultSet.getString("item");
                float price = resultSet.getFloat("price");
                String currency = resultSet.getString("currency");

                MenuItem menuItem = new MenuItem(menuItemID, category, item, price, currency);
                menuItems.add(menuItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuItems;
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        String query = "INSERT INTO menuitems(menuitemid, category, item, price, currency) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menuItem.getId());
            statement.setString(2, menuItem.getCategory());
            statement.setString(3, menuItem.getItem());
            statement.setFloat(4, menuItem.getPrice());
            statement.setString(5, menuItem.getCurrency());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuItem;
    }
}
