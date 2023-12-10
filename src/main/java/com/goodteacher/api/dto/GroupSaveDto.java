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

    // TODO: Checking if teacher exists
    @NotNull
    private Long teacherId;

    // TODO: Checking if student exists
    @NotNull
    private Set<Long> studentIds;
}
