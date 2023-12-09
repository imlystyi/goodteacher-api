package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSaveDto {
    @NotNull
    @NotBlank
    private String name;

    private String about;

    @NotNull
    private TeacherDto teacher;

    @NotNull
    private Set<StudentDto> students;
}
