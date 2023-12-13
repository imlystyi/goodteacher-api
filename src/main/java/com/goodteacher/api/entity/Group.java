package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity that represents a group of {@link Student} with {@link Teacher} as a curator.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String about;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "groups")
    private List<Student> students;

    @Column
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}
