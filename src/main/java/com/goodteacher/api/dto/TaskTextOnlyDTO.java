package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskTextOnlyDTO {
    // region Fields
    @NotNull
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    private String text;
    private LocalDate creationDate;

    // endregion

    // region Methods

    // endregion
}
