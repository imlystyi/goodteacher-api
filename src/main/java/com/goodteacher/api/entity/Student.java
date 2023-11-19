package com.goodteacher.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// todo: create Student entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {
    @Id
    private UUID id;

    @Column
    private int entryYear;

    //private Set<Assignment> assignments;

    // todo-i4:

}
