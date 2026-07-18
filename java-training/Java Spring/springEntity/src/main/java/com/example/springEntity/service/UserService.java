package com.example.springEntity.service;

import com.example.springEntity.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User createWithCascade(User user);
    User createWithoutCascade(User user);
    User getById(Long id);
    List<User> getAll();
    User update(Long id, User user);
    void delete(Long id);
    Page<User> findActiveUsers();
}
