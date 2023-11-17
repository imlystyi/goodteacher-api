package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTextDTO {
    private UUID id;
    private String name;
    private String text;

    public static TaskTextDTO toDTO(final Task task){
        final TaskTextDTO taskTextDTO = new TaskTextDTO();

        taskTextDTO.setId(task.getId());
        taskTextDTO.setName(task.getName());
        taskTextDTO.setText(task.getText());

        return taskTextDTO;
    }

}
