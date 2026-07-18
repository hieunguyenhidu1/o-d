package com.example.springEntity.dto;

import com.example.springEntity.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Date createdAt;
    private User.Status status;
    private List<OrderDto> orders;
}
