package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.CreateScheduleRequest;
import com.example.scheduledevelop.schedule.dto.CreateScheduleResponse;
import com.example.scheduledevelop.schedule.dto.GetScheduleResponse;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(request));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll());
    }


}
