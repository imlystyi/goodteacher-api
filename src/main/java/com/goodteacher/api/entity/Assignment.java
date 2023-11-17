package com.goodteacher.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity - на майбутнє, до БД (дописати атотації до полів)
public class Assignment {
    private UUID id;
    private Task task;
    //private Student student;
    //private Teacher teacher;
    private Double grade;
    private LocalDate deadline;

}
