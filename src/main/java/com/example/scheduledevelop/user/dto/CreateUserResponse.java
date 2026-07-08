package com.example.scheduledevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponse {

    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;

    public CreateUserResponse(Long userId, String userName, String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
    }
}
