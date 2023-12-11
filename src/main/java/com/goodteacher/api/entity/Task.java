package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String text;

    @Column
    private String quiz;

    @Column
    private LocalDate creationDate;

    @Column
    private String authorName;

    @Column
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}
