package com.example.springEntity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.EAGER) // Mặc định là EAGER với @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key
    private User user;

}
