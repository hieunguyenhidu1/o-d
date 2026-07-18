package com.example.springEntity.service.impl;

import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import com.example.springEntity.repository.OrderRepository;
import com.example.springEntity.repository.UserRepository;
import com.example.springEntity.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final OrderRepository repoOrder;
    private final EntityManager entityManager;

    public UserServiceImpl(UserRepository repo, OrderRepository repoOrder, EntityManager entityManager) {
        this.repo = repo;
        this.repoOrder = repoOrder;
        this.entityManager = entityManager;
    }

    public User createWithCascade(User user) {
        user.setCreatedAt(new java.util.Date());
        if (user.getOrders() != null) {
            for (Order order : user.getOrders()) {
                order.setUser(user); // ❗ BẮT BUỘC để thiết lập mối quan hệ ngược
            }
        }

        return repo.save(user);
//        entityManager.persist(user);
//        return user;
    }

    public User createWithoutCascade(User user){
        user.setCreatedAt(new java.util.Date());
        User savedUser = repo.save(user);//tao user co id de gan cho order
        if (user.getOrders() != null) {
            for (Order order : user.getOrders()) {
                order.setUser(savedUser); // ❗ BẮT BUỘC để thiết lập mối quan hệ ngược
                repoOrder.save(order);
            }
        }

//        entityManager.persist(user);
        return user;
    }

    @Cacheable(value = "userById", key = "#id")
    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    @Caching(evict = {
            @CacheEvict(value = "userById", key = "#id"),
            @CacheEvict(value = "activeUsersPage", allEntries = true)
    })
    public User update(Long id, User user) {
        User existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setStatus(user.getStatus());
            return repo.save(existing);
        }
        return null;
    }

    @Caching(evict = {
            @CacheEvict(value = "userById", key = "#id"),
            @CacheEvict(value = "activeUsersPage", allEntries = true)
    })
    public void delete(Long id) {
        User user = repo.findById(id).orElseThrow();

        //Xoa user la xoa order
        repo.delete(user);

//        user.getOrders().removeIf(order -> order.getId().equals(orderId));
//        userRepo.save(user); // or entityManager.merge(user);
    }

    @Cacheable(value = "activeUsersPage")
    public Page<User> findActiveUsers(){
        return repo.findByStatus(User.Status.ACTIVE, PageRequest.of(
                0,
                5,
                Sort.by("createdAt").descending()));
    }
}
