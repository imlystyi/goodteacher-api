package com.goodteacher.api.service;

import com.goodteacher.api.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskDTO save(final TaskDTO taskDTO);

    TaskDTO findDTOById(UUID id);

    void deleteById(UUID id);

    List<TaskDTO> findTasksByTeacherName(String teacherName);

    List<TaskDTO> findTaskByName(String taskName);
}
