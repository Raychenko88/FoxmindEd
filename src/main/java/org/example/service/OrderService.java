package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order) throws Exception;

    Order update(Order order) throws Exception;

    Order findById(Integer id) throws Exception;

    List<Order> findAllByUserId(Integer id);

    void delete(Order order);
}
