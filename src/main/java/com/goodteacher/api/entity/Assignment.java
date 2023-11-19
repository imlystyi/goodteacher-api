package com.goodteacher.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// todo-db: field annotations
// todo: create Assignment entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assignment {
    @Id
    private UUID id;


//    private Task task;
//
//    // todo: uncomment when Student and Teacher are implemented
//
//    private Student student;
//    private Teacher teacher;
//    private Double grade;
//    private LocalDate deadline;

}
