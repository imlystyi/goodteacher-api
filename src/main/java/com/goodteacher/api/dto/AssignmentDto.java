package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {
    private Long id;

    private String title;

    private TaskDto task;

    private StudentDto student;

    private TeacherDto teacher;

    private Double grade;

    private String comment;

    private LocalDate deadline;

    private LocalDate closingDate;

    private Boolean isClosed;
}