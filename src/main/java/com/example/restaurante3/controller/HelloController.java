package com.example.restaurante3.controller;

import com.example.restaurante3.HelloApplication;
import com.example.restaurante3.domain.Order;
import com.example.restaurante3.domain.Table;
import com.example.restaurante3.dto.Order2OrderDTOMapper;
import com.example.restaurante3.dto.OrderDTO;
import com.example.restaurante3.repo.db.MenuItemRepoDB;
import com.example.restaurante3.repo.db.OrderItemRepoDB;
import com.example.restaurante3.service.Service;
import com.example.restaurante3.utils.event.OrderEntityChangeEvent;
import com.example.restaurante3.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class HelloController implements Observer<OrderEntityChangeEvent> {
    @FXML
    private TableView<OrderDTO> orderItemStaffTableView;
    @FXML
    private TableColumn<OrderDTO, Integer> tableIDColumn;
    @FXML
    private TableColumn<OrderDTO, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<OrderDTO, String> foodColumn;

    private final ObservableList<OrderDTO> orderModel = FXCollections.observableArrayList();

    private final Order2OrderDTOMapper order2OrderDTOMapper = new Order2OrderDTOMapper(new OrderItemRepoDB(), new MenuItemRepoDB());

    private Service service;
    @FXML
    public Label staffLabelID;

    public void setService(Service service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    public void setLabel(String text){
        staffLabelID.setText(text);
    }

    @FXML
    public void initialize(){
        tableIDColumn.setCellValueFactory(new PropertyValueFactory<>("tableID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        foodColumn.setCellValueFactory(new PropertyValueFactory<>("food"));

        orderItemStaffTableView.setItems(orderModel);
    }

    public void initModel(){
        orderModel.setAll(order2OrderDTOMapper.convert(service.getAllOrders()));
    }


    public void showTables() throws IOException {
        List<Table> tables = service.getAllTables();
        for(Table table: tables){
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("tables-view.fxml"));
            Scene scene = new Scene(loader.load(), 320, 240);
            Stage stage = new Stage();
            stage.setTitle("Table");
            stage.setScene(scene);

            TablesViewController tablesViewController = loader.getController();
            tablesViewController.setLabel(table.getId().toString());
            tablesViewController.setMenuItemList(service.getAllMenuItems());
            tablesViewController.setService(service);

            stage.show();
            tablesViewController.showMenu();
        }
    }

    @Override
    public void update(OrderEntityChangeEvent orderEntityChangeEvent) {
        initModel();
    }
}