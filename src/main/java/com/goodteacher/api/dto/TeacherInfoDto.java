package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Teacher;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that represents {@link Teacher} entity info.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoDto {
    @NotNull
    private Long id;

    private String about;

    private String status;
}
