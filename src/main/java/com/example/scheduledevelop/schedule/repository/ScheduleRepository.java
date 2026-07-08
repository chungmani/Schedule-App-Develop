package com.example.scheduledevelop.schedule.repository;

import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUser(User user);
}
