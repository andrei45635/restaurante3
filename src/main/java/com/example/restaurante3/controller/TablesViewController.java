package com.example.restaurante3.controller;

import com.example.restaurante3.domain.MenuItem;
import com.example.restaurante3.domain.Order;
import com.example.restaurante3.domain.OrderItem;
import com.example.restaurante3.domain.OrderStatus;
import com.example.restaurante3.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class TablesViewController {
    private Service service;

    @FXML
    private VBox vBoxID;

    public List<MenuItem> menuItemList = new ArrayList<>();

    public List<TableView<MenuItem>> menuViews = new ArrayList<>();

    @FXML
    private Label tableNoID;

    public void setLabel(String text){
        tableNoID.setText(text);
    }

    public void setMenuItemList(List<MenuItem> menuItems){
        this.menuItemList = menuItems;
    }

    public void setService(Service service){
        this.service = service;
    }

    public void showMenu() {
        Set<String> categories = new HashSet<>();
        for(MenuItem items: this.menuItemList){
            categories.add(items.getCategory());
        }

        for(String category: categories){
            vBoxID.getChildren().add(new Label(category));
            TableView<MenuItem> currentItem = new TableView<>();
            menuViews.add(currentItem);
            TableColumn<MenuItem, String> columnName = new TableColumn<>();
            TableColumn<MenuItem, String> columnPrice = new TableColumn<>();
            columnName.setCellValueFactory(new PropertyValueFactory<>("item"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("priceCurrency"));
            currentItem.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            currentItem.getColumns().setAll(Arrays.asList(columnName, columnPrice));

            vBoxID.getChildren().add(currentItem);

            List<MenuItem> itemeCategorie = menuItemList.stream().filter(menuItem -> menuItem.getCategory().equals(category)).collect(Collectors.toList());
            ObservableList<MenuItem> menuItemModel = FXCollections.observableArrayList();
            menuItemModel.setAll(itemeCategorie);
            currentItem.setItems(menuItemModel);
        }
    }

    @FXML
    private void onClickPlaceOrder(ActionEvent actionEvent) {
        int currentTableID = Integer.parseInt(tableNoID.getText());
        Order order = null;
        OrderItem orderItem = null;
        for(var cc: menuViews){
            var selected = cc.getSelectionModel().getSelectedCells();
            for(var s: selected){
                var val = s.getTableColumn().getCellObservableValue(s.getRow()).getValue();
                order = new Order(new Random().nextInt(90) + 10,  currentTableID, LocalDateTime.now(), OrderStatus.PLACED);
                for(MenuItem mi : service.getAllMenuItems()){
                    if(Objects.equals(mi.getItem(), val.toString())){
                        orderItem = new OrderItem(new Random().nextInt(90) + 10, order.getId(), mi.getId(),1);
                    }
                }
            }
        }
        service.addOrder(order, orderItem);
    }
}
