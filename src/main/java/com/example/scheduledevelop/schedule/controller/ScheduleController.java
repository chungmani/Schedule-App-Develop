package com.example.scheduledevelop.schedule.controller;

import com.example.scheduledevelop.schedule.dto.*;
import com.example.scheduledevelop.schedule.service.ScheduleService;
import com.example.scheduledevelop.user.dto.SessionUser;
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
            @SessionAttribute(name = "loginUser") SessionUser loginUser,
            @RequestBody CreateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(loginUser.getId(), request));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getSchedules(
            @SessionAttribute(name = "loginUser") SessionUser loginUser
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(loginUser.getId()));
    }

    //단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(
            @SessionAttribute(name = "loginUser") SessionUser loginUser,
            @PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(loginUser.getId(), scheduleId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @SessionAttribute(name = "loginUser") SessionUser loginUser,
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(loginUser.getId(), scheduleId, request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = "loginUser") SessionUser loginUser,
            @PathVariable Long scheduleId) {
        scheduleService.delete(loginUser.getId(), scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
