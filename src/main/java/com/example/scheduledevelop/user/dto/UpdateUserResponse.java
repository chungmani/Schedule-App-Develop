package com.example.scheduledevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime modifiedAt;

    public UpdateUserResponse(Long id, String userName, String email, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.modifiedAt = modifiedAt;
    }
}
