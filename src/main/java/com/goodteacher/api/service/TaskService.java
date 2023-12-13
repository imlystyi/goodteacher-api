package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;
import com.goodteacher.api.entity.Task;

import java.util.Set;

public interface TaskService {
    TaskDto findById(Long id);

    Task findEntityById(Long id);

    Set<TaskDto> findAllByName(String name);

    Set<TaskDto> findAllByAuthorName(String authorName);

    TaskDto save(TaskSaveDto taskSaveDto);

    void delete(Long id);
}
