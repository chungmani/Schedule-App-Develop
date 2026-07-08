package com.example.scheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String author;
    private String title;
    private String content;
}
