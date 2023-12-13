package com.goodteacher.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private LocalDate birthDate;

    private String about;

    private String status;

    @Builder.Default
    private Set<Long> groupIds = Set.of();
}
