package com.example.restaurante3.repo.db;

import com.example.restaurante3.domain.Table;
import com.example.restaurante3.repo.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRepoDB implements IRepository<Integer, Table> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Table> findAll() {
        List<Table> tables = new ArrayList<>();
        String query = "SELECT * FROM tables";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                int id = resultSet.getInt("tableid");

                Table table = new Table(id);
                tables.add(table);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tables;
    }

    @Override
    public Table save(Table table) {
        String query = "INSERT INTO tables(tableid) VALUES (?)";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, table.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return table;
    }
}
