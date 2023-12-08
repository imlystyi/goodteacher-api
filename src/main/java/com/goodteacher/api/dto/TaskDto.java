package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
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
public class TaskDto {
    // region Fields

    private Long id;
    private String name;
    private String text;
    private String quiz;
    private LocalDate creationDate;

    // endregion

    // region Methods

    public static TaskDto toDto(final Task task) {
        final TaskDto taskDTO = new TaskDto();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setText(task.getText());
        taskDTO.setQuiz(task.getQuiz());
        taskDTO.setCreationDate(task.getCreationDate());

        return taskDTO;
    }

    // endregion
}
