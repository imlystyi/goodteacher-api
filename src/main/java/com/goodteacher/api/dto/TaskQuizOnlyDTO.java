package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskQuizOnlyDTO {
    private UUID id;
    private String name;
    private String quiz;

    public static TaskQuizOnlyDTO toDTO(final Task task){
        final TaskQuizOnlyDTO taskQuizOnlyDTO = new TaskQuizOnlyDTO();

        taskQuizOnlyDTO.setId(task.getId());
        taskQuizOnlyDTO.setName(task.getName());
        taskQuizOnlyDTO.setQuiz(task.getQuiz());

        return taskQuizOnlyDTO;
    }
}
