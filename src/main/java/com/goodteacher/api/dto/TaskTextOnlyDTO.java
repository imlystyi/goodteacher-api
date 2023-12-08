package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTextOnlyDTO {
    // region Fields

    private Long id;
    private String name;
    private String text;

    // endregion

    // region Methods

    public static TaskTextOnlyDTO toDTO(final Task task) {
        final TaskTextOnlyDTO taskTextOnlyDTO = new TaskTextOnlyDTO();

        taskTextOnlyDTO.setId(task.getId());
        taskTextOnlyDTO.setName(task.getName());
        taskTextOnlyDTO.setText(task.getText());

        return taskTextOnlyDTO;
    }

    // endregion
}
