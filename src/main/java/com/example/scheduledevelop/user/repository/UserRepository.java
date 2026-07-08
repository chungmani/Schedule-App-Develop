package com.example.scheduledevelop.user.repository;

import com.example.scheduledevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
