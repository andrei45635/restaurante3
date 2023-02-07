package com.example.restaurante3.repo.db;

import com.example.restaurante3.domain.OrderItem;
import com.example.restaurante3.repo.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepoDB implements IRepository<Integer, OrderItem> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM orderitems";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int orderID = resultSet.getInt("orderid");
                int menuItemID = resultSet.getInt("menuitemid");
                int quantity = resultSet.getInt("quantity");
                int id = resultSet.getInt("id");

                OrderItem orderItem = new OrderItem(id, orderID, menuItemID, quantity);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderItems;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        String query = "INSERT INTO orderitems(orderid, menuitemid, quantity, id) VALUES (?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, orderItem.getOrderID());
            statement.setInt(2, orderItem.getMenuItemID());
            statement.setInt(3, orderItem.getQuantity());
            statement.setInt(4, orderItem.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderItem;
    }
}
