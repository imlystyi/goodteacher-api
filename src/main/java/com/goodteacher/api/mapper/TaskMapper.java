// BAD YET

package com.goodteacher.api.mapper;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;

public class TaskMapper {
    public static TaskDto fromEntityToDto(final Task entity){
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .text(entity.getText())
                .quiz(entity.getQuiz())
                .creationDate(entity.getCreationDate())
                .authorName(entity.getAuthorName())
                .build();
    }

    public static Task fromDtoToEntity(final TaskDto saveDto) {
        return Task.builder()
                .id(saveDto.getId())
                .name(saveDto.getName())
                .text(saveDto.getText())
                .quiz(saveDto.getQuiz())
                .creationDate(saveDto.getCreationDate())
                .authorName(saveDto.getAuthorName())
                .build();
    }

    public static Task fromSaveDtoToEntity(TaskSaveDto saveDto) {
        return Task.builder()
                .name(saveDto.getName())
                .text(saveDto.getText())
                .quiz(saveDto.getQuiz())
                .creationDate(saveDto.getCreationDate())
                .authorName(saveDto.getAuthorName())
                .build();
    }
}
