package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.CreateUserRequest;
import com.example.scheduledevelop.user.dto.CreateUserResponse;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getUserId(), savedUser.getUserName(),
                savedUser.getEmail(), savedUser.getCreatedAt());
    }
}
