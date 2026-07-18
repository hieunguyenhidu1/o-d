package com.example.springEntity.dto;

import com.example.springEntity.entity.User;
import lombok.Getter;

@Getter
public class UserRepoDTO {
    private Long id;
    private String username;
    private String email;
    private User.Status status;

    public UserRepoDTO(Long id, String username, String email, User.Status status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
    }


}
