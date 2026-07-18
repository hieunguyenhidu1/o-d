package com.example.springEntity.dto;

public class UserOrderInfo {
    private String username;
    private String email;
    private Long totalOrders;

    public UserOrderInfo(String username, String email, Long totalOrders) {
        this.username = username;
        this.email = email;
        this.totalOrders = totalOrders;
    }

    // Getters nếu không dùng Lombok
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }
}
