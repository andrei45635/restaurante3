package com.example.restaurante3.dto;

import com.example.restaurante3.domain.MenuItem;
import com.example.restaurante3.domain.Order;
import com.example.restaurante3.domain.OrderItem;
import com.example.restaurante3.repo.db.MenuItemRepoDB;
import com.example.restaurante3.repo.db.OrderItemRepoDB;

import java.util.ArrayList;
import java.util.List;

public class Order2OrderDTOMapper {

    private final OrderItemRepoDB orderItemRepoDB;
    private final MenuItemRepoDB menuItemRepoDB;

    public Order2OrderDTOMapper(OrderItemRepoDB orderItemRepoDB, MenuItemRepoDB menuItemRepoDB) {
        this.orderItemRepoDB = orderItemRepoDB;
        this.menuItemRepoDB = menuItemRepoDB;
    }

    public OrderDTO convert(Order order){
        for(OrderItem oi: orderItemRepoDB.findAll()){
            if(oi.getOrderID() == order.getId()){
                for(MenuItem mi : menuItemRepoDB.findAll()){
                    if(mi.getId() == oi.getMenuItemID()){
                        return new OrderDTO(order.getTableID(), order.getDate(), mi.getItem());
                    }
                }
            }
        }
        return null;
    }

    public List<OrderDTO> convert(List<Order> orders){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order o: orders){
            orderDTOS.add(convert(o));
        }
        return orderDTOS;
    }
}
