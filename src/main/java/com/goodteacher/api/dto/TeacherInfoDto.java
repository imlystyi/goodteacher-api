package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoDto {
    private Long id;

    private String about;

    private String status;
}
