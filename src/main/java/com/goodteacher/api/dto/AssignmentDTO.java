package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Student;
import com.goodteacher.api.entity.Teacher;
import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    private UUID id;
    private Task task;
    private Student student;
    private Teacher teacher;
    private Double grade;
    private LocalDate deadline;
}