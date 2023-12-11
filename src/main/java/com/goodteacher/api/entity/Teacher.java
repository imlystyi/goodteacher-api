package com.goodteacher.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="teachers")
public class Teacher extends User {
    @Column
    private String about;

    @Column
    private String status;

    @OneToMany
    @JoinTable(name = "teacher_groups",
               joinColumns = @JoinColumn(name = "teacher_id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
}
