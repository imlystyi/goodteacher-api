package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO that represents {@link Student} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String patronymic;

    private LocalDate birthDate;

    @Builder.Default
    private Set<AssignmentDto> assignments = Set.of();

    @Builder.Default
    private Set<Long> groupIds = Set.of();
}
