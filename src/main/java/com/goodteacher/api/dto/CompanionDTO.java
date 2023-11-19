package com.goodteacher.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanionDTO {
    private UUID id;
    private String name;
    private String surname;
    private String patronymic;
}
