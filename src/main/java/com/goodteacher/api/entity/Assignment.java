package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity that represents {@link Task} that assigned by {@link Teacher} for specific {@link Student}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column
    private Double grade;

    @Column
    private String comment;

    @Column
    private LocalDate deadline;

    @Column
    private LocalDate closingDate;

    @Column
    @Builder.Default
    private Boolean isClosed = Boolean.FALSE;

    @Column
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}
