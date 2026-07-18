package com.example.springEntity.service.impl;

import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import com.example.springEntity.repository.OrderRepository;
import com.example.springEntity.repository.UserRepository;
import com.example.springEntity.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    public OrderServiceImpl(OrderRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    public Order create(Order order, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            order.setUser(user);
            if (order.getOrderDate() == null)
                order.setOrderDate(new java.util.Date());
            return orderRepo.save(order);
        }
        return null;
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Order update(Long id, Order order) {
        Order existing = orderRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setOrderDate(order.getOrderDate());
            return orderRepo.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }
}
