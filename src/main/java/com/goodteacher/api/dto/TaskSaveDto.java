package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSaveDto {
    @NotNull
    private String name;

    private String text;

    private String quiz;

    @NotNull
    private LocalDate creationDate;

    private String authorName;
}
