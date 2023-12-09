package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
@SuperBuilder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String patronymic;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;

    @Column(nullable = false)
    private int entryYear;

    @OneToMany(mappedBy = "student")
    private Set<Assignment> assignments;

    @ManyToMany
    @JoinTable(name = "students_groups",
               joinColumns = @JoinColumn(name = "student_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;
}
