package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a {@link Task} assigned by the {@link Teacher} for a specific {@link Student}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    private UUID id;

    // todo: check persistence
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column
    private Double grade;

    @Column
    private LocalDate deadline;

    @Column
    private Boolean isActive = Boolean.TRUE;
}
