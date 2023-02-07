package com.example.restaurante3.repo.db;

import com.example.restaurante3.domain.Order;
import com.example.restaurante3.domain.OrderStatus;
import com.example.restaurante3.repo.IRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepoDB implements IRepository<Integer, Order> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders ORDER by orderdate";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int orderID = resultSet.getInt("orderid");
                int tableID = resultSet.getInt("tableid");
                LocalDateTime orderDate = resultSet.getTimestamp("orderdate").toLocalDateTime();
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));

                Order order = new Order(orderID, tableID, orderDate, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public Order save(Order order) {
        String query = "INSERT INTO orders(orderid, tableid, orderdate, status) VALUES (?, ?, ?, ?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, order.getId());
            statement.setInt(2, order.getTableID());
            statement.setTimestamp(3, Timestamp.valueOf(order.getDate()));
            statement.setString(4, String.valueOf(order.getStatus()));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
