package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "students")
public class Student extends User {
    // TODO: Bidirectional updating
    @OneToMany(mappedBy = "student")
    private List<Assignment> assignments;

    @ManyToMany
    @JoinTable(name = "students_groups",
               joinColumns = @JoinColumn(name = "student_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
}
