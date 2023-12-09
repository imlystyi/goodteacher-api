package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDto;
import com.goodteacher.api.dto.TaskSaveDto;

import java.util.Set;

public interface TaskService {
    TaskDto findById(Long id);

    Set<TaskDto> findAllByName(String name);

    Set<TaskDto> findAllByAuthorName(String authorName);

    TaskDto save(TaskSaveDto taskSaveDto);

    void delete(Long id);
}
