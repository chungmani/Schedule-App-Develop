package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.*;
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

    //단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
