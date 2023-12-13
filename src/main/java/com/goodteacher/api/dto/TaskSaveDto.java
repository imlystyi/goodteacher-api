package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import com.goodteacher.api.service.TaskService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO that represents {@link Task} entity for making a save.
 *
 * @see TaskService#save(TaskSaveDto)
 */
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
