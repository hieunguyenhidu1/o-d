package com.example.springEntity.service;

import com.example.springEntity.dto.OrderDto;
import com.example.springEntity.dto.UserDto;
import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setStatus(user.getStatus());

        List<OrderDto> orders = user.getOrders().stream().map(this::toDto).collect(Collectors.toList());
        dto.setOrders(orders);

        return dto;
    }

    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
