package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSaveDto {
    @NotNull
    private String title;

    @NotNull
    private TaskDTO task;

    @NotNull
    private StudentDto student;

    @NotNull
    private TeacherDto teacher;

    private Double grade;

    private String comment;

    private LocalDate deadline;

    private LocalDate closingDate;

    @NotNull
    private Boolean isClosed;
}