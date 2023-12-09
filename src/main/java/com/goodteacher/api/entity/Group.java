package com.goodteacher.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="groups")
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String about;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "groups")
    private Set<Student> students;

    @Column
    @Builder.Default
    private Boolean isActive = Boolean.TRUE;
}
