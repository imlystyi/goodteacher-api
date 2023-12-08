package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskDto save(final TaskDto taskDTO);

    TaskDto findDTOById(Long id);

    void deleteById(Long id);

/*    List<TaskDto> findTasksByTeacherName(String teacherName);*/

    List<TaskDto> findTaskByName(String taskName);
}
