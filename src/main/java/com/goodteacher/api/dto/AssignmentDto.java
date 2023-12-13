package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Assignment;
import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Task;
import com.goodteacher.api.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO that represents {@link Assignment} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {
    private Long id;

    private String title;

    private TaskDto task;

    private Long teacherId;

    private Long studentId;

    private Double grade;

    private String comment;

    private LocalDate deadline;

    private LocalDate closingDate;

    private Boolean isClosed;
}