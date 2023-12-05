package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String nickname;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
}
