package com.example.scheduledevelop.schedule.entity;

import com.example.scheduledevelop.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    public Schedule(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
