package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.*;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getUserId(), savedUser.getUserName(),
                savedUser.getEmail(), savedUser.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetUserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new GetUserResponse(
                        user.getUserId(), user.getUserName(), user.getEmail(),
                        user.getCreatedAt(), user.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetUserResponse findOne(Long userId) {
        User user = getOrThrow(userId);
        return new GetUserResponse(
                user.getUserId(), user.getUserName(), user.getEmail(),
                user.getCreatedAt(), user.getModifiedAt());
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = getOrThrow(userId);
        user.updateUser(request.getUserName(), request.getEmail());
        return new UpdateUserResponse(user.getUserId(), user.getUserName(), user.getEmail(), user.getModifiedAt());
    }

    @Transactional
    public void delete(Long userId) {
        User user = getOrThrow(userId);
        userRepository.deleteById(user.getUserId());
    }

    // 공통메서드
    private User getOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다. id: " + userId
                )
        );
    }



}
