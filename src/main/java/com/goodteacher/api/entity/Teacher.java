package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Entity that represents a user with the teacher role.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="teachers")
public class Teacher extends User {
    @Column
    private String about;

    @Column
    private String status;

    @Builder.Default
    @OneToMany
    @JoinTable(name = "teacher_groups",
               joinColumns = @JoinColumn(name = "teacher_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups = List.of();
}
