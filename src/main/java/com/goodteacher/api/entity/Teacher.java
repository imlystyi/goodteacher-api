package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teachers")
public class Teacher {
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

    @Column
    private String about;

    @Column
    private String status;

    @OneToMany
    @JoinTable(name = "teacher_groups",
               joinColumns = @JoinColumn(name = "teacher_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    @Builder.Default
    private Set<Group> groups = Set.of();
}
