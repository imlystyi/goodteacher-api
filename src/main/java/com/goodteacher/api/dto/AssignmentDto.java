package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {
    private UUID id;
    private String title;
    private TaskDTO task;
    private StudentDto student;
    private TeacherDto teacher;
    private Double grade;
    private String comment;
    private LocalDate deadline;
    private LocalDate closingDate;
}