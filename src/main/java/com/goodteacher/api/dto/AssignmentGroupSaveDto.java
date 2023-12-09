package com.goodteacher.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentGroupSaveDto {
    @NotNull
    private String title;

    @NotNull
    private TaskDto task;

    private LocalDate deadline;

    @NotNull
    private Boolean isClosed;
}
