package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.schedule.repository.ScheduleRepository;
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
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse create(Long userId, CreateScheduleRequest request) {
        User user = userOrThrow(userId);
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(Long userId) {
        User user = userOrThrow(userId);
        List<Schedule> schedules = scheduleRepository.findByUser(user);

        return schedules.stream()
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(), schedule.getTitle(), schedule.getContent(),
                        schedule.getCreatedAt(), schedule.getModifiedAt()
                ))
                .toList();
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long userId, Long scheduleId) {
        User user = userOrThrow(userId);
        Schedule schedule = getOrThrow(scheduleId);
        return new GetScheduleResponse(
                schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = getOrThrow(scheduleId);
        schedule.updateSchedule(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(
                schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleId);
        scheduleRepository.deleteById(schedule.getId());
    }

    // 공통메서드
    private Schedule getOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다." + scheduleId
                ));
    }

    private User userOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다. userId: " + userId
                ));
    }

}
