package com.goodteacher.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
}
