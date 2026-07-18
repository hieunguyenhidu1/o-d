package com.example.springEntity.repository;

import com.example.springEntity.entity.User;

public interface UserView {
    Long getId();
    String getUsername();
    User.Status getStatus();
}
