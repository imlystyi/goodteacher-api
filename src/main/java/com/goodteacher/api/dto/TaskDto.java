package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO that represents {@link Task} entity.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    private String name;

    private String text;

    private String quiz;

    private LocalDate creationDate;

    private String authorName;
}
