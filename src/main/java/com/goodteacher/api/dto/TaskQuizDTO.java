package com.goodteacher.api.dto;

import com.goodteacher.api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskQuizDTO {
    private UUID id;
    private String name;
    private String quiz;

    public static TaskQuizDTO toDTO(final Task task){
        final TaskQuizDTO taskQuizDTO = new TaskQuizDTO();

        taskQuizDTO.setId(task.getId());
        taskQuizDTO.setName(task.getName());
        taskQuizDTO.setQuiz(task.getQuiz());

        return taskQuizDTO;
    }
}
