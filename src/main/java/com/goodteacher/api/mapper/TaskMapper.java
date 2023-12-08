package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.entity.Task;

public class TaskMapper {
    public static TaskDto fromEntityToDto(final Task entity){
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .text(entity.getText())
                .quiz(entity.getQuiz())
                .creationDate(entity.getCreationDate())
                .build();
    }

/*    public static Task fromSignUpDtoToEntity(final Task signUpDto){
        return Task.builder()
                .id(entity.getId())
                .name(entity.getName())
                .text(entity.getText())
                .quiz(entity.getQuiz())
                .creationDate(entity.getCreationDate())
                .build();
    }*/
}
