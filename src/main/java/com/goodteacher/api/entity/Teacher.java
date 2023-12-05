package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teachers")
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {
    @Column
    private String about;

    // TODO-1: Vladyslav: Provide status validation
    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name = "teacher_groups",
               joinColumns = @JoinColumn(name = "teacher_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;
}
