package com.example.springEntity.service;

import com.example.springEntity.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order, Long userId);
    Order getById(Long id);
    List<Order> getAll();
    Order update(Long id, Order order);
    void delete(Long id);
}
