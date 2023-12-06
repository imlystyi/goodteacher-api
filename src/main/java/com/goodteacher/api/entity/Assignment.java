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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String title = null;

    @ManyToOne
    @JoinColumn(name = "task_id",
                nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student_id",
                nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id",
                nullable = false)
    private Teacher teacher;

    @Column
    private Double grade = null;

    @Column
    private String comment = null;

    @Column
    private LocalDate deadline = null;

    @Column
    private LocalDate closingDate = null;

    @Column
    private Boolean isActive = Boolean.TRUE;
}
