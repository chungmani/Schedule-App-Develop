package com.example.scheduledevelop.schedule.service;

import com.example.scheduledevelop.schedule.dto.CreateScheduleRequest;
import com.example.scheduledevelop.schedule.dto.CreateScheduleResponse;
import com.example.scheduledevelop.schedule.dto.GetScheduleResponse;
import com.example.scheduledevelop.schedule.entity.Schedule;
import com.example.scheduledevelop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<GetScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(), schedule.getAuthor(), schedule.getTitle(),
                        schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ))
                .toList();
    }
}
