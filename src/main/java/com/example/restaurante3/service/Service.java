package com.example.restaurante3.service;

import com.example.restaurante3.domain.MenuItem;
import com.example.restaurante3.domain.Order;
import com.example.restaurante3.domain.OrderItem;
import com.example.restaurante3.domain.Table;
import com.example.restaurante3.repo.db.MenuItemRepoDB;
import com.example.restaurante3.repo.db.OrderItemRepoDB;
import com.example.restaurante3.repo.db.OrderRepoDB;
import com.example.restaurante3.repo.db.TableRepoDB;
import com.example.restaurante3.utils.event.ChangeTypeEvent;
import com.example.restaurante3.utils.event.OrderEntityChangeEvent;
import com.example.restaurante3.utils.observer.Observable;
import com.example.restaurante3.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<OrderEntityChangeEvent> {
    private TableRepoDB tableIRepository = new TableRepoDB();
    private MenuItemRepoDB menuItemIRepository = new MenuItemRepoDB();
    private OrderRepoDB orderRepoDB = new OrderRepoDB();
    private OrderItemRepoDB orderItemRepoDB = new OrderItemRepoDB();
    private List<Observer<OrderEntityChangeEvent>> observers = new ArrayList<>();

    public Service(TableRepoDB tableIRepository, MenuItemRepoDB menuItemIRepository, OrderRepoDB orderRepoDB, OrderItemRepoDB orderItemRepoDB) {
        this.tableIRepository = tableIRepository;
        this.menuItemIRepository = menuItemIRepository;
        this.orderRepoDB = orderRepoDB;
        this.orderItemRepoDB = orderItemRepoDB;
    }

    public List<Table> getAllTables(){
        return tableIRepository.findAll();
    }

    public List<MenuItem> getAllMenuItems(){
        return menuItemIRepository.findAll();
    }

    public List<Order> getAllOrders(){return orderRepoDB.findAll();}

    public void addOrder(Order order, OrderItem orderItem){
        orderRepoDB.save(order);
        orderItemRepoDB.save(orderItem);
        this.notifyObservers(new OrderEntityChangeEvent(ChangeTypeEvent.ADD, order));
    }

    @Override
    public void addObserver(Observer<OrderEntityChangeEvent> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(OrderEntityChangeEvent orderEntityChangeEvent) {
        observers.forEach(x -> x.update(orderEntityChangeEvent));
    }
}
