package com.goodteacher.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// todo: create Teacher entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {
    @Column
    private String about;

    // todo: status validation in DTO
    @Column
    private String status;
}
