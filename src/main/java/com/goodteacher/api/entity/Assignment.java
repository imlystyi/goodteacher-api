package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a {@link Task} assigned by the {@link Teacher} for a specific {@link Student}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

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
    private Double grade;

    @Column
    private String comment;

    @Column
    private LocalDate deadline;

    @Column
    private LocalDate closingDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isClosed = Boolean.FALSE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}
