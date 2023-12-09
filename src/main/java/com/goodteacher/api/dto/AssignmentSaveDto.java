package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSaveDto {
    @NotNull
    private String title;

    @NotNull
    private TaskDto task;

    @NotNull
    private StudentDto student;

    @NotNull
    private TeacherDto teacher;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    @NotNull
    private Boolean isClosed;
}