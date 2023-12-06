package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {
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
