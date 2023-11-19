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
    private UUID id;
    private String name;
    private String text;

    public static TaskTextOnlyDTO toDTO(final Task task){
        final TaskTextOnlyDTO taskTextOnlyDTO = new TaskTextOnlyDTO();

        taskTextOnlyDTO.setId(task.getId());
        taskTextOnlyDTO.setName(task.getName());
        taskTextOnlyDTO.setText(task.getText());

        return taskTextOnlyDTO;
    }

}
