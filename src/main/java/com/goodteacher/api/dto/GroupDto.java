package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;

    private String name;

    private String about;

    private TeacherDto teacher;

    private Set<StudentDto> students;
}
