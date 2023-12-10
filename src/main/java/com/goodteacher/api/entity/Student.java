package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String patronymic;

    @Column
    private LocalDate birthDate;

    @Column
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    @Column
    private int entryYear;

    // TODO: Fix sets updating
    @OneToMany(mappedBy = "student")
    private List<Assignment> assignments;

    @ManyToMany
    @JoinTable(name = "students_groups",
               joinColumns = @JoinColumn(name = "student_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
}
