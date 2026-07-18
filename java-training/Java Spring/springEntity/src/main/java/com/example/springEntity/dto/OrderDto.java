package com.example.springEntity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private Date orderDate;
}
