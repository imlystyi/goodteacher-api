package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO that represents {@link Group} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private Long id;

    private String name;

    private String about;

    private TeacherDto teacher;

    private Set<StudentDto> students;
}
