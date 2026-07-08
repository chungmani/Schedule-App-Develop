package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.schedule.repository.ScheduleRepository;
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

    @Transactional
    public CreateScheduleResponse create(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getAuthor(), request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getAuthor(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(), schedule.getAuthor(), schedule.getTitle(),
                        schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleId);
        return new GetScheduleResponse(
                schedule.getId(), schedule.getAuthor(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = getOrThrow(scheduleId);
        schedule.updateSchedule(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(
                schedule.getId(), schedule.getAuthor(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    // 공통메서드
    private Schedule getOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다." + scheduleId
                ));
    }

}
